package edu.gatech.spacetraders.entity;
import java.util.Random;

/**
 * Enum for Tech Levels
 */
public enum TechLevel {
    PREAGRIGCULTURE ("Pre-Agriculture"),
    AGRICULTURE     ("Agriculture"),
    MEDIEVAL        ("Medieval"),
    RENAISSANCE     ("Renaissance"),
    EARLYINDUSTRIAL ("Early Industrial"),
    INDUSTRIAL      ("Industrial"),
    POSTINDUSTRIAL  ("Post-Industrial"),
    HITECH          ("Hi-Tech");

    private final String name;
    TechLevel(String name) {
        this.name = name;
    }

    /**
     * Returns a random tech level
     * @return a random tech level
     */
    /*public static TechLevel getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }*/
    /**
     * Returns the name of a
     * @return a random tech level
     */
    String getName() {return name;}
}
