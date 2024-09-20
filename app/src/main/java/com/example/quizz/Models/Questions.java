package com.example.quizz.Models;

import java.io.Serializable;
import java.util.Map;

public class Questions implements Serializable {
    private String correctAnswer;
    private Map<String, String> options;
    private String questionText;


    public Questions() {
    }


    public Questions(String correctAnswer, Map<String, String> options, String questionText) {
        this.correctAnswer = correctAnswer;
        this.options = options;
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
