package edu.gatech.spacetraders.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Ship implements Serializable {
    private final int NUM_GOODS = 10;
    private int curFuel;
    private final int maxCargo;
    private int curCargo; //current total cargo space occupied
    private EnumMap<Good, Integer> cargoHold = new EnumMap<>(Good.class); //keeps track of # of each item
    private final List<String> recycleViewList = new ArrayList<>(10);

    public Ship() {
        this(null);
    }

    public Ship(int maxCargo) {
        this.maxCargo = maxCargo;
    }

    private Ship(EnumMap<Good, Integer> cargoHold) {
        if (cargoHold == null) {
            populateCargoSpace();
        } else {
            this.cargoHold = cargoHold;
        }
        int maxFuel = ShipType.GNAT.fuel();
        this.maxCargo = ShipType.GNAT.cargo();
        this.curFuel = maxFuel;
        this.curCargo = 0;
    }

    public int getCargoSpace() {
        return this.maxCargo - this.curCargo;
    }

    public boolean cantHoldMore() {
        return curCargo >= maxCargo;
    }

    private void populateCargoSpace() {
        for (Good good : Good.values()) {
            cargoHold.put(good, 0);
        }
    }

    public int getMaxCargo() {
        return maxCargo;
    }

    public void setCurCargo(int curCargo) {
        this.curCargo = curCargo;
    }

    public int getCurCargo() {
        return curCargo;
    }

    public EnumMap<Good, Integer> getCargoHold() {
        return cargoHold;
    }

    public void setCargoHold(EnumMap<Good, Integer> cargo) {cargoHold = cargo;}

    public int getCurFuel() {
        return curFuel;
    }

    public void setCurFuel(int curFuel) {
        this.curFuel = curFuel;
    }
}