package com.example.pokemonapplication.model;

import java.util.List;

public class PokemonInfo {
    private int id;
    private String name;
    private int height;
    private int weight;
    private int experience;
    private List<String> type;

    public PokemonInfo() {
    }

    public PokemonInfo(int id, String name, int height, int weight, int experience, List<String> type) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.experience = experience;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PokemonInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", experience=" + experience +
                ", type=" + type +
                '}';
    }
}
