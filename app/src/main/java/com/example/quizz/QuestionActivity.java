// src/main/java/com/example/quizz/QuestionActivity.java
package com.example.quizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizz.Models.Questions;
import com.example.quizz.Models.Nivel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button submitButton;
    private TextView feedbackText;

    private List<Questions> questions;
    private int currentQuestionIndex = 0;
    private String correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionText = findViewById(R.id.question_text);
        optionsGroup = findViewById(R.id.options_group);
        submitButton = findViewById(R.id.submit_button);
        feedbackText = findViewById(R.id.feedback_text);


        Intent intent = getIntent();
        ArrayList<Questions> questionsList = (ArrayList<Questions>) intent.getSerializableExtra("questions");

        if (questionsList != null) {
            questions = questionsList;
            Collections.shuffle(questions);
            showNextQuestion();
        }

        submitButton.setOnClickListener(v -> {
            RadioButton selectedOption = findViewById(optionsGroup.getCheckedRadioButtonId());
            if (selectedOption != null) {
                String selectedAnswer = selectedOption.getText().toString();
                if (selectedAnswer.trim().equalsIgnoreCase(correctAnswer.trim())) {
                    feedbackText.setText("Correto!");
                    feedbackText.setTextColor(getResources().getColor(R.color.colorCorrect));
                } else {
                    feedbackText.setText("Errado! A resposta correta é " + correctAnswer);
                    feedbackText.setTextColor(getResources().getColor(R.color.colorIncorrect));
                }

                feedbackText.setVisibility(View.VISIBLE);

                new android.os.Handler().postDelayed(() -> {
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()) {
                        showNextQuestion();
                    } else {

                        Intent intent1 = new Intent(QuestionActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }, 2000);
            }
        });
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Questions question = questions.get(currentQuestionIndex);
            questionText.setText(question.getQuestionText());
            Map<String, String> options = question.getOptions();
            ((RadioButton) findViewById(R.id.option_a)).setText(options.get("a"));
            ((RadioButton) findViewById(R.id.option_b)).setText(options.get("b"));
            ((RadioButton) findViewById(R.id.option_c)).setText(options.get("c"));
            ((RadioButton) findViewById(R.id.option_d)).setText(options.get("d"));

            correctAnswer = options.get(question.getCorrectAnswer());
            feedbackText.setVisibility(View.GONE);
        }
    }
}
