package com.demo.pokepb.entity;

public class Pokemon {
    private int id;
    private String name;

    public Pokemon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
