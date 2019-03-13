package edu.gatech.spacetraders.entity;

import java.io.Serializable;

import static edu.gatech.spacetraders.entity.ShipType.*;

public class Ship {
    private final int NUM_GOODS = 10;
    private int maxFuel;
    private int curFuel;
    private int maxCargo;
    private int curCargo; //current total cargo space occupied
    private int[] cargoHold = new int[NUM_GOODS]; //keeps track of # of each item

    public Ship() {
        this(GNAT);
    }

    public Ship(ShipType type) {
        this(type, new int[10]);
    }

    public Ship(ShipType type, int[] cargoHold) {
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
        for (int i = 0; i < NUM_GOODS; i++) {
            ans += cargoHold[i];
        }
        return ans;
    }
}
