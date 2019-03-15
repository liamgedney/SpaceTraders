package edu.gatech.spacetraders.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static edu.gatech.spacetraders.entity.ShipType.*;

public class Ship {
    private final int NUM_GOODS = 10;
    private int maxFuel;
    private int curFuel;
    private int maxCargo;
    private int curCargo; //current total cargo space occupied
    private EnumMap<Good, Integer> cargoHold = new EnumMap<>(Good.class); //keeps track of # of each item
    private List<String> recycleViewList = new ArrayList<>(10);

    public Ship() {
        this(GNAT);
    }

    public Ship(ShipType type) {
        for (Good good : Good.values()) {
            cargoHold.put(good, 0);
        }
    }

    public Ship(ShipType type, EnumMap<Good, Integer> cargoHold) {
        this.cargoHold = cargoHold;
        this.maxFuel = type.fuel();
        this.maxCargo = type.cargo();
        this.curFuel = maxFuel;
        this.curCargo = 0;
    }

    public int getCargoSpace() {
        return this.maxCargo - this.curCargo;
    }

    public boolean cantHoldMore() {
        if (curCargo >= maxCargo) {
            return true;
        } else {
            return false;
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
}