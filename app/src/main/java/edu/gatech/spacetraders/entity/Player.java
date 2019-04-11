package edu.gatech.spacetraders.entity;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class Player implements Serializable{

    private String playerName;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private Difficulty difficulty;
    private int credits;
    private Ship ship;
    private List<String> recycleViewList = new ArrayList<>(10);
    private final EnumMap<Good, Integer> cargo;


    public Player(String playerName, int[] pointsArr, Difficulty difficulty) {
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

    String getPlayerName() {
        return playerName;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCredits() {
        return credits;
    }

    private int getCargo(Good good) { return cargo.get(good); }

    public Ship getShip() {
        return ship;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void upCargo(int position, int amount) {
        Good good = Good.values()[position];
        cargo.put(good, getCargo(good) + amount);
        ship.setCargoHold(cargo);
    }

    public void downCredits(EnumMap<Good, Integer> prices, int position, int amount) {
        Good good = Good.values()[position];
        int price = prices.get(good);
        int credits = getCredits();
        setCredits(credits - (price * amount));
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean canSell(int position) {
        Good good = Good.values()[position];
        if (cargo.get(good) < 1) {
            Log.d("","Cannot sell more items than currently in inventory.");
            return false;
        }
        return true;
    }

    public void sell(int position) {
        sell(position, 1);
    }

    private void sell(int position, int amount) {
        Good good = Good.values()[position];
        cargo.put(good, getCargo(good) - amount);
        ship.setCargoHold(cargo);
        ship.setCurCargo(ship.getCurCargo() - amount);
    }

    public void upCredits(EnumMap<Good, Integer> prices, int position) {
        upCredits(prices, position,1 );
    }

    private void upCredits(EnumMap<Good, Integer> prices, int position, int amount) {
        Good good = Good.values()[position];
        int price = prices.get(good);
        int credits = getCredits();
        setCredits(credits + (price * amount));
    }

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
