package edu.gatech.spacetraders.entity;


import android.support.annotation.NonNull;

import java.io.Serializable;

public class SolarSystem implements Serializable {
    private final String name;
    private final int techLvl;
    private final int resLvl;
    private final Point2 coordinates;
    private static Market market;

    SolarSystem(String name, int techLvl, int reslvl, Player player, Ship ship) {
        this(name, techLvl, reslvl, player, ship, 0, 0);
    }

    SolarSystem(String name, int techLvl, int reslvl, Player player, Ship ship, int x, int y) {
        this.name = name;
        this.techLvl = techLvl;
        this.resLvl = reslvl;
        Player player1 = player;
        Ship ship1 = ship;
        this.coordinates = new Point2(x, y);
        this.market = new Market(techLvl, player, ship);

    }

    public int getTechLvl() {
        return techLvl;
    }

    public Market getMarket() { return market; }

    public void setMarket(Market m) { market = m; }

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