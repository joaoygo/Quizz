package com.example.quizz.Models;

import java.util.Map;

public class Categories {
    private String name;
    private Map<String, Nivel> niveis;

    public Categories() {
    }

    public Categories(String name, Map<String, Nivel> niveis) {
        this.name = name;
        this.niveis = niveis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Nivel> getNiveis() {
        return niveis;
    }

    public void setNiveis(Map<String, Nivel> niveis) {
        this.niveis = niveis;
    }
}
