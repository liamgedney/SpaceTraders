package edu.gatech.spacetraders.entity;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

/**
 * Player class
 */
public class Player implements Serializable{

    private final String playerName;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private final Difficulty difficulty;
    private int credits;
    private Ship ship;
    private List<String> recycleViewList = new ArrayList<>(10);
    private final EnumMap<Good, Integer> cargo;


    /**
     * Player constructor
     * @param playerName name
     * @param pointsArr array of how points are distributed
     * @param difficulty level
     */
    public Player(String playerName, Difficulty difficulty, int ... pointsArr) {
        this.playerName = playerName;
        this.pilotPoints = pointsArr[0];
        this.fighterPoints = pointsArr[1];
        this.traderPoints = pointsArr[2];
        this.engineerPoints = pointsArr[3];
        this.difficulty = difficulty;
        this.credits = 1000;
        this.ship = new Ship();
        cargo = ship.getCargoHold();
    }

    /*String getPlayerName() {
        return playerName;
    }*/

    /**
     * pilot getter
     * @return int points
     */
    public int getPilotPoints() {
        return pilotPoints;
    }

    /**
     * fighter getter
     * @return int fighter
     */
    public int getFighterPoints() {
        return fighterPoints;
    }

    /**
     * trader getter
     * @return int trader
     */
    public int getTraderPoints() {
        return traderPoints;
    }

    /**
     * engineer getter
     * @return int points
     */
    public int getEngineerPoints() {
        return engineerPoints;
    }


    /*public Difficulty getDifficulty() {
        return difficulty;
    }*/

    /**
     * credit getter
     * @return int credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * getter for cargo
     * @param good from cargo
     * @return int good number
     */
    private int getCargo(Good good) {
        int cargoNum;
        if (good == null) {
            throw new NullPointerException("Good parameter cannot be null.");
        } else {
            cargoNum = Objects.requireNonNull(cargo.get(good));
        }
        return cargoNum;
    }

    /**
     * constructor ship
     * @return ship
     */
    public Ship getShip() {
        return ship;
    }


    /*public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }*/

    /**
     * set pilot points
     * @param pilotPoints points
     */
    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    /**
     * sets fighter points
     * @param fighterPoints points
     */
    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    /**
     * set trader points
     * @param traderPoints points
     */
    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    /**
     * set engineer points
     * @param engineerPoints points
     */
    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }


    /*public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }*/

    /**
     * sets credits
     * @param credits money
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * increases cargo
     * @param position of good
     * @param amount amount
     */
    public void upCargo(int position, int amount) {
        Good good = Good.values()[position];
        cargo.put(good, getCargo(good) + amount);
        ship.setCargoHold(cargo);
    }

    /**
     * decreases credits
     * @param prices of good
     * @param position of good
     * @param amount of good
     */
    public void downCredits(EnumMap<Good, Integer> prices, int position, int amount) {
        Good good = Good.values()[position];
        int price;
        int credits;
        if (prices == null || good == null) {
            throw new NullPointerException("Parameters cannot be null.");
        } else {
            price = Objects.requireNonNull(prices.get(good));
            credits = getCredits();
        }
        setCredits(credits - (price * amount));
    }

    /**
     * ship setter
     * @param ship set
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * can we sell
     * @param position of good
     * @return boolean whether we can
     */
    public boolean canSell(int position) {
        Good good = Good.values()[position];
        int inventoryNum;
        if (good == null) {
            throw new NullPointerException("Good cannot be null.");
        } else {
            inventoryNum = Objects.requireNonNull(cargo.get(good));
        }
        if (inventoryNum < 1) {
            Log.d("","Cannot sell more items than currently in inventory.");
            return false;
        }
        return true;
    }
/*
    *
     * can we sell this much
     * @param position of good
     * @return boolean can sell*/
    /*private boolean canSell(int position, int amount) {
        Good good = Good.values()[position];
        if (cargo.get(good) < amount) {
            Log.d("","Cannot sell more items than currently in inventory.");
            return false;
        }
        return true;
    }*/

    /**
     * sell
     * @param position good
     */
    public void sell(int position) {
        sell(position, 1);
    }

    /**
     * sell
     * @param position of good
     * @param amount of good
     */
    private void sell(int position, int amount) {
        Good good = Good.values()[position];
        cargo.put(good, getCargo(good) - amount);
        ship.setCargoHold(cargo);
        ship.setCurCargo(ship.getCurCargo() - amount);
    }

    /**
     * increase credits
     * @param prices by amount
     * @param position from position
     */
    public void upCredits(EnumMap<Good, Integer> prices, int position) {
        upCredits(prices, position,1 );
    }

    private void upCredits(EnumMap<Good, Integer> prices, int position, int amount) {
        Good good = Good.values()[position];
        int price;
        int credits;
        if (good == null) {
            throw new NullPointerException("Good cannot be null.");
        } else {
            price = Objects.requireNonNull(prices.get(good));
            credits = getCredits();
        }
        setCredits(credits + (price * amount));
    }

    /**
     * make list of market
     * @param market class
     * @return List<String>
     */
    public List<String> makeList(Market market) {
        int count = 0;
        for (Good good: Good.values()) {
            String newString = count + " ";
            newString += String.format("%1$11s", good.toString());
            newString += String.format("%1$5s", "$" + market.getPrice(good));
            newString += String.format("%1$5s", "" + cargo.get(good));
            recycleViewList.add(newString);
            count++;
        }
        return Collections.unmodifiableList(recycleViewList);
    }

    /**
     * update the list
     * @param market class
     */
    public void updateList(Market market) {
        List<String> list =  new ArrayList<>(10);
        int count = 0;
        for (Good good: Good.values()) {
            String newString = count + " ";
            newString += String.format("%1$11s", good.toString());
            newString += String.format("%1$5s", "$" + market.getPrice(good));
            newString += String.format("%1$5s", "" + cargo.get(good));
            list.add(newString);
            count++;
        }
        recycleViewList = list;
    }

    /**
     * getter for List
     * @return List<String>
     */

    public List<String> getList() {
        return Collections.unmodifiableList(recycleViewList);
    }


    @NonNull
    @Override
    public String toString() {
        return String.format("PLAYER:\nName: %s, Pilot Points: %d, Fighter Points: %d "
                        + "Trader Points: %d, Engineer Points: %d, "
                        + "Difficulty: %s, Credits: %d, Ship Type: %s",
                playerName, pilotPoints, fighterPoints, traderPoints,
                engineerPoints, difficulty, credits, ship);
    }
}
