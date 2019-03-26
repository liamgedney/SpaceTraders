package edu.gatech.spacetraders.entity;

import java.io.Serializable;
import java.util.EnumMap;

public class Player {

    private String playerName;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private Difficulty difficulty;
    private int credits;
    private Ship ship;
    private EnumMap<Good, Integer> cargo = new EnumMap<>(Good.class);

    public Player(String playerName, int pilotPoints, int fighterPoints,
                  int traderPoints, int engineerPoints, Difficulty difficulty) {
        this.playerName = playerName;
        this.pilotPoints = pilotPoints;
        this.fighterPoints = fighterPoints;
        this.traderPoints = traderPoints;
        this.engineerPoints = engineerPoints;
        this.difficulty = difficulty;
        this.credits = 1000;
        this.ship = new Ship(ShipType.GNAT);
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

    public int getCargo(Good good) { return cargo.get(good); }

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
        setCredits(credits - price * amount);
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean canSell(Good good, int amount) {
        if (cargo.get(good) > amount) {
            System.out.println("Cannot sell more items than currently in inventory.");
            return false;
        }
        return true;
    }

    public void sell(int position) {
        sell(position, 1);
    }

    public void sell(int position, int amount) {
        Good good = Good.values()[position];
        if (canSell(good, amount)) {
            cargo.put(good, getCargo(good) - amount);
            ship.setCargoHold(cargo);
        }
    }

    public Market updateMarket(Market market, int position) {
        return updateMarket(market, position, 1);
    }

    public Market updateMarket(Market market, int position, int amount) {
        Good good = Good.values()[position];
        if (canSell(good, amount)) {
            market.upInventory(position, amount);
        }
        return market;
    }

    public void updatePlayer(Market market, int position, int amount) {
        Good good = Good.values()[position];
        if (canSell(good, amount)) {
            EnumMap<Good, Integer> prices = market.getPrices();
            downCredits(prices, position, amount);
        }
    }

    public void upCredits(EnumMap<Good, Integer> prices, int position) {
        upCredits(prices, position,1 );
    }

    public void upCredits(EnumMap<Good, Integer> prices, int position, int amount) {
        Good good = Good.values()[position];
        int price = prices.get(good);
        int credits = getCredits();
        setCredits(credits + price * amount);
    }


    @Override
    public String toString() {
        return String.format("PLAYER:\nName: %s, Pilot Points: %d, Fighter Points: %d "
                            + "Trader Points: %d, Engineer Points: %d, "
                            + "Difficulty: %s, Credits: %d, Ship Type: %s",
                            playerName, pilotPoints, fighterPoints, traderPoints,
                            engineerPoints, difficulty, credits, ship);
    }
}
