package com.example.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.Adapters.CategoriesAdapter;
import com.example.quizz.Models.Categories;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Categories> listCat;
    DatabaseReference databaseReference;
    CategoriesAdapter catAdapter;
    ImageButton btnMoveToProfile;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        databaseReference = FirebaseDatabase.getInstance().getReference("quiz");
        listCat = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        catAdapter = new CategoriesAdapter(this, listCat);
        recyclerView.setAdapter(catAdapter);
        btnMoveToProfile = findViewById(R.id.profile_button);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("MainActivity", "Dados recebidos: " + snapshot);
                for (DataSnapshot categorySnapshot: snapshot.child("categories").getChildren()) {
                    Categories category = categorySnapshot.getValue(Categories.class);
                    if (category != null) {
                        listCat.add(category);
                    }
                }

                catAdapter.notifyDataSetChanged();
                Log.d("MainActivity", "Dados recebidos: " + listCat.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnMoveToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "Botão de perfil clicado");
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}