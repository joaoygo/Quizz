package com.example.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.Adapters.NiveisAdapter;
import com.example.quizz.Models.Nivel;
import com.example.quizz.Models.Questions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class NiveisActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NiveisAdapter niveisAdapter;
    private ArrayList<Nivel> listNiveis;
    private DatabaseReference databaseReference;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveis);

        recyclerView = findViewById(R.id.recycler_view_niveis);
        listNiveis = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        niveisAdapter = new NiveisAdapter(this, listNiveis);
        recyclerView.setAdapter(niveisAdapter);
        categoryName = getIntent().getStringExtra("categoryName");
        if (categoryName != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference("quiz/categories/" + categoryName.toLowerCase() + "/niveis");
        } else {
            Log.e("NiveisActivity", "categoryName é nulo");
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listNiveis.clear();
                for (DataSnapshot nivelSnapshot : snapshot.getChildren()) {
                    Nivel nivel = nivelSnapshot.getValue(Nivel.class);
                    if (nivel != null) {
                        listNiveis.add(nivel);
                    }
                }
                niveisAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("NiveisActivity", "Erro ao ler dados", error.toException());
            }
        });

        niveisAdapter.setOnItemClickListener(nivel -> {
            Log.d("ClickActivityNivel", "Clicked on level: " + nivel.getName());

            DatabaseReference questionsRef = FirebaseDatabase.getInstance()
                    .getReference("quiz/categories/" + categoryName.toLowerCase() + "/niveis/" + nivel.getName() + "/questions");
            Log.d("ClickActivityNivel", "Clicked on level: " + questionsRef);
            questionsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ArrayList<Questions> questionsList = new ArrayList<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Questions question = dataSnapshot.getValue(Questions.class);
                        if (question != null) {
                            questionsList.add(question);
                        }
                    }

                    Intent intent = new Intent(NiveisActivity.this, QuestionActivity.class);
                    intent.putExtra("questions", questionsList);
                    intent.putExtra("categoryName", categoryName);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("NiveisActivity", "Erro ao ler perguntas", error.toException());
                }
            });
        });
    }
}
