package com.example.springboot;

import static com.example.springboot.Colors.*;

public class Cat {
    public int id;
    public String name;
    public Colors color;
    public int race;
    public int owner;

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", race=" + race +
                ", owner=" + owner +
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

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Cat() {
    }

    public Cat(int id, String name, String color, int race, int owner) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.owner = owner;
        if (color.compareTo("white") == 0) {
            this.color = white;
        } else if (color.compareTo("orange") == 0) {
            this.color = orange;
        } else if (color.compareTo("black") == 0) {
            this.color = black;
        }

    }
}

