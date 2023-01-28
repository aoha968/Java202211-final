package com.demo.pokepb.entity;

public class Pokemon {
    private int id;
    private String name;
    private String type1;
    private String type2;

    public Pokemon(int id, String name, String type1, String type2) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }

    public int getId() {
        return id;
    }
    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
}
