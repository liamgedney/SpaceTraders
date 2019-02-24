package edu.gatech.spacetraders.entity;
public class SolarSystem {
    private String name;
    private String techLvl;
    private String resLvl;

    SolarSystem(String name, String techLvl, String reslvl) {
        this.name = name;
        this.techLvl = techLvl;
        this.resLvl = reslvl;
    }

    public String toString() {
        return "Name: " + this.name + " Tech Level: " + this.techLvl
                + " Resource Level: " + this.resLvl;
    }
}