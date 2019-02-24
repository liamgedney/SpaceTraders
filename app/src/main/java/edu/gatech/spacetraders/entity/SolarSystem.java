package edu.gatech.spacetraders.entity;

import android.graphics.Point;

public class SolarSystem {
    private String name;
    private int techLvl;
    private int resLvl;
    private Point coordinates;

    SolarSystem(String name, int techLvl, int reslvl) {
        this(name, techLvl, reslvl, 0, 0);
    }

    SolarSystem(String name, int techLvl, int reslvl, int x, int y) {
        this.name = name;
        this.techLvl = techLvl;
        this.resLvl = reslvl;
        this.coordinates = new Point(x, y);
    }

    public String toString() {
        return "Name: " + this.name + ", Tech Level: " + this.techLvl
                + ", Resource Level: " + this.resLvl + ", Coordinates: "
                + coordinates;
    }



}