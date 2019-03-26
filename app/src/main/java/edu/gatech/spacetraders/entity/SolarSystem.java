package edu.gatech.spacetraders.entity;

import android.graphics.Point;

import java.io.Serializable;

public class SolarSystem {
    private String name;
    private int techLvl;
    private int resLvl;
    private Point coordinates;
    private static Market market;
    private Player player;
    private Ship ship;

    SolarSystem(String name, int techLvl, int reslvl, Player player, Ship ship) {
        this(name, techLvl, reslvl, player, ship, 0, 0);
    }

    SolarSystem(String name, int techLvl, int reslvl, Player player, Ship ship, int x, int y) {
        this.name = name;
        this.techLvl = techLvl;
        this.resLvl = reslvl;
        this.player = player;
        this.ship = ship;
        this.coordinates = new Point(x, y);
        this.market = new Market(techLvl, player, ship);

    }

    public int getTechLvl() {
        return techLvl;
    }

    public Market getMarket() { return market; }

    public void setMarket(Market m) { market = m; }

    public String toString() {
        return "Name: " + this.name + ", Tech Level: " + this.techLvl
                + ", Resource Level: " + this.resLvl + ", Coordinates: "
                + coordinates;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}