package edu.gatech.spacetraders.entity;

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
    String getName() {return name;}
}
