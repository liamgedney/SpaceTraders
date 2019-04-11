package edu.gatech.spacetraders.entity;


import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.EnumMap;

/**
 * represents a solarsystem - There are 10 in the universe.
 */
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

    /**
     * getter for techLvl
     * @return this solar system's tech level
     */
    public int getTechLvl() {
        return techLvl;
    }

    /**
     * getter for market
     * @return this solar system's market
     */
    public Market getMarket() { return market; }

    /**
     * setter for market
     * @param m the new market for this solar system
     */
    public void setMarket(Market m) {
        market = m;
    }

    @NonNull
    public String toString() {
        return "Name: " + this.name + ", Tech Level: " + this.techLvl
                + ", Resource Level: " + this.resLvl + ", Coordinates: "
                + coordinates;
    }

    /**
     * getter for coordinates
     * @return this solar system's coordinates
     */
    public Point2 getCoordinates() {
        return coordinates;
    }

    /**
     * returns this SS's market's prices
     * @return this SS's market's prices
     */
    public EnumMap<Good, Integer> getPrices() {
        return market.getPrices();
    }
}