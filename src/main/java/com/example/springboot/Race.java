package com.example.springboot;


public class Race {
    public int id;
    public String name;

    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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

    public Race(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Race() {
    }
}
