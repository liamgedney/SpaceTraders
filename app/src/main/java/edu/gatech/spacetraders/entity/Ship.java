package edu.gatech.spacetraders.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Ship class
 */
public class Ship implements Serializable {
    //private final int NUM_GOODS = 10;
    private int curFuel;
    private final int maxCargo;
    private int curCargo; //current total cargo space occupied
    private EnumMap<Good, Integer> cargoHold = new EnumMap<>(Good.class); //keeps track of # item
    //private final List<String> recycleViewList = new ArrayList<>(10);

    /**
     * constructor for ship
     */
    public Ship() {
        this(null);
    }

    /**
     * constructor with maxCargo
     //* @param maxCargo cargo hold
     */
    /*public Ship(int maxCargo) {
        this.maxCargo = maxCargo;
    }*/

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

    /**
     * getter for cargo space
     * @return int space left
     */
    public int getCargoSpace() {
        return this.maxCargo - this.curCargo;
    }

    /**
     * can we hold more
     * @return boolean yes or no
     */
   /* public boolean cantHoldMore() {
        return curCargo >= maxCargo;
    }*/

    private void populateCargoSpace() {
        for (Good good : Good.values()) {
            cargoHold.put(good, 0);
        }
    }

    /**
     * getter maxCargo
     * @return maxCargo the max
     */
    public int getMaxCargo() {
        return maxCargo;
    }

    /**
     * setter current cargo
     * @param curCargo current cargo
     */
    public void setCurCargo(int curCargo) {
        this.curCargo = curCargo;
    }

    /**
     * getter current Cargo
     * @return int cargo
     */
    public int getCurCargo() {
        return curCargo;
    }

    /**
     * get cargoHold
     * @return EnumMap<Good, Integer> map
     */
    public EnumMap<Good, Integer> getCargoHold() {
        return cargoHold;
    }

    /**
     * set Cargo hold
     * @param cargo from enum map
     */
    public void setCargoHold(EnumMap<Good, Integer> cargo) {cargoHold = cargo;}

    /**
     * gets current fuel
     * @return int curFuel
     */
    public int getCurFuel() {
        return curFuel;
    }

    /**
     * set the current fuel
     * @param curFuel number
     */
    /*public void setCurFuel(int curFuel) {
        this.curFuel = curFuel;
    }*/
}