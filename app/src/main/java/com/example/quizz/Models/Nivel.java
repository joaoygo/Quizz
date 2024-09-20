package com.example.quizz.Models;

import java.util.Map;

public class Nivel {
    private String name;
    private Map<String, Questions> questions;

    public Nivel() {
    }

    public Nivel(String name, Map<String, Questions> questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, Questions> questions) {
        this.questions = questions;
    }
}
