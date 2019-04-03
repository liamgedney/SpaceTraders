package edu.gatech.spacetraders.entity;
import java.io.Serializable;
import java.util.Random;

public enum TechLevel implements Serializable{
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

    public static TechLevel getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    String getName() {return name;}
}
