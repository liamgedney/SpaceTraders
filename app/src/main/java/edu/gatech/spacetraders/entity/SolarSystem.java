package edu.gatech.spacetraders.entity;
public class SolarSystem {
    private String name;
    private int techLvl;
    private int resLvl;

    SolarSystem(String name, int techLvl, int reslvl) {
        this.name = name;
        this.techLvl = techLvl;
        this.resLvl = reslvl;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Tech Level: " + this.techLvl
                + " Resource Level: " + this.resLvl;
    }
}