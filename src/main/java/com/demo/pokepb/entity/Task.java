package com.demo.pokepb.entity;

public class Task {
    private int id;
    private String detail;

    public Task(int id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }
}
