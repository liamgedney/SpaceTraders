package edu.gatech.spacetraders.entity;

import java.util.EnumMap;

import static edu.gatech.spacetraders.entity.ShipType.*;

public class Ship {
    private final int NUM_GOODS = 10;
    private int maxFuel;
    private int curFuel;
    private int maxCargo;
    private int curCargo; //current total cargo space occupied
    private EnumMap<Good, Integer> cargoHold = new EnumMap<>(Good.class); //keeps track of # of each item

    public Ship() {
        this(GNAT);
    }

    public Ship(ShipType type) {
        this(type, null);
    }

    public Ship(ShipType type, EnumMap<Good, Integer> cargoHold) {
        this.cargoHold = cargoHold;
        this.maxFuel = type.fuel();
        this.maxCargo = type.cargo();
        this.curFuel = maxFuel;
        this.curCargo = cargoCalc();
    }

    /*
    helper method calculates total size of cargo hold
     */
    private int cargoCalc() {
        int ans = 0;
        for (Good good : Good.values()) {
            ans += cargoHold.get(good);
        }
        return ans;
    }

    public int getCargoSpace() {
        return this.maxCargo - this.curCargo;
    }
}