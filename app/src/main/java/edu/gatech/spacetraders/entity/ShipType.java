package edu.gatech.spacetraders.entity;

import java.io.Serializable;

public enum ShipType {
    //name      fuel    cargo
    FLEA        (20,    5),
    GNAT        (10,    10),
    FIREFLY     (17,    20),
    MOSQUITO    (13,    15),
    BUMBLEBEE   (15,    20),
    BEETLE      (14,    50),
    HORNET      (16,    20),
    GRASSHOPPER (16,    30),
    TERMITE     (13,    60),
    WASP        (14,    35);

    private final int fuel;
    private final int cargo;
    ShipType(int fuel, int cargo) {
        this.fuel = fuel;
        this.cargo = cargo;
    }
    public int fuel() { return fuel; }
    public int cargo() { return cargo; }
}
