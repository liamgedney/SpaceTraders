package edu.gatech.spacetraders.entity;


import android.support.annotation.NonNull;

import java.io.Serializable;

public class SolarSystem implements Serializable {
    private final String name;
    private final int techLvl;
    private final int resLvl;
    private final Point2 coordinates;
    private static Market market;

    SolarSystem(String name, int[] levels, Player player, Ship ship, int[] coords) {
        this.name = name;
        this.techLvl = levels[0];
        this.resLvl = levels[1];
        this.coordinates = new Point2(coords[0], coords[1]);
        market = new Market(techLvl, player, ship);

    }

    public int getTechLvl() {
        return techLvl;
    }

    public Market getMarket() { return market; }

    public void setMarket(Market m) {
        market = m;
    }

    @NonNull
    public String toString() {
        return "Name: " + this.name + ", Tech Level: " + this.techLvl
                + ", Resource Level: " + this.resLvl + ", Coordinates: "
                + coordinates;
    }

    public Point2 getCoordinates() {
        return coordinates;
    }
}