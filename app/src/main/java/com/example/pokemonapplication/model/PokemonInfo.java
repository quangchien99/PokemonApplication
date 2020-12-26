package com.example.pokemonapplication.model;

import com.example.pokemonapplication.utils.Const;

import java.util.List;
import java.util.Random;

public class PokemonInfo {
    private int id;
    private String name;
    private int height;
    private int weight;
    private int experience;
    private List<String> type;

    private Random random = new Random();
    private int hp = random.nextInt(Const.MAX_HP);
    private int def = random.nextInt(Const.MAX_DEF);
    private int atk = random.nextInt(Const.MAX_ATK);
    private int speed = random.nextInt(Const.MAX_SPEED);
    private int exp = random.nextInt(Const.MAX_EXP);

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

    public String getHpString() {
        return Integer.toString(hp) + "/" + Const.MAX_HP;
    }

    public String getDefString() {
        return Integer.toString(def) + "/" + Const.MAX_DEF;
    }

    public String getAtkString() {
        return Integer.toString(atk) + "/" + Const.MAX_ATK;
    }

    public String getSpeedString() {
        return Integer.toString(speed) + "/" + Const.MAX_SPEED;
    }

    public int getHp() {
        return hp;
    }

    public int getDef() {
        return def;
    }

    public int getAtk() {
        return atk;
    }

    public int getSpeed() {
        return speed;
    }

    public int getExp() {
        return exp;
    }

    public String getExpString() {
        return Integer.toString(exp) + "/" + Const.MAX_EXP;
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
