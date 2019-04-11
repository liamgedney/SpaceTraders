package edu.gatech.spacetraders.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

/**
 * Class for Market
 */
public class Market implements Serializable {
    private final int techLevel;
    //use an enum map to track prices and amount in stock of each good
    private final EnumMap<Good, Integer> prices = new EnumMap<>(Good.class);
    private final EnumMap<Good, Integer> inventory = new EnumMap<>(Good.class);
    private final Player player;
    private final Ship ship;
    private List<String> recycleViewList = new ArrayList<>(10);
    private final List<Good> goods = new ArrayList<>(10);
    GameData gameData = GameDataInstanceGetter.getGameData();


    /**
     * constructor. makes a new market with params:
     *
     * @param techLevel system tech level
     * @param player the player shopping
     * @param ship the player's ship
     */
    public Market(int techLevel, Player player, Ship ship) {
        this.techLevel = techLevel;
        this.player = player;
        this.ship = ship;
        for (Good good : Good.values()) {
            prices.put(good, calculatePrice(techLevel, good));
            inventory.put(good, calculateAmount(techLevel, good));
            goods.add(good);
        }
        makeList();
    }

    /**
     * calculates price for given good using good- and system-specific info
     *
     * @param techLevel the tech level of the current system
     * @param good the good we are calculating price for
     * @return the price
     */
    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + (good.ipl() * (techLevel - good.mtlp()));
    }

    /**
     * calculates the amount of starting inventory for given good using:
     *
     * @param techLevel the tech level of the current system
     * @param good the good we are calculating price for
     * @return the amount
     */
    private int calculateAmount(int techLevel, Good good) {
        int amt = 10 * ((techLevel - good.mtlp()) + 1);
        if (amt < 0) {
            return 0;
        } else {
            return amt;
        }
    }


    public int getPrice(Good good) {
        return prices.get(good);
    }

    public boolean canBuy(int position) {
        return canBuy(position, 1);
    }

    /**
     * helper method determines if the player has enough money and space to
     * buy, and the system market has enough of the good to sell, the given
     * amount of the given good.
     *
     * @param position the good to check
     * @param amount the amount of the good we want to buy
     * @return if we can buy it or not
     */
    private boolean canBuy(int position, int amount) {
        Good good = Good.values()[position];
        if (this.techLevel < good.mtlu()) {
            System.out.println("This planet cannot produce this good.");
            return false;
        } else if (player.getCredits() < (prices.get(good) * amount)) {
            System.out.println("You don't have enough credits to buy this item.");
            return false;
        } else if (ship.getCargoSpace() == 0) {
            System.out.println("You don't have enough cargo space.");
            return false;
        } else if (inventory.get(good) < amount) {
            System.out.println("This shop is all out of this good");
            return false;
        }
        return true;
    }

    public void buy(int position) {
        buy(position, 1);
    }

    private int getInventory(Good good) {
        return inventory.get(good);
    }

    private void buy(int position, int amount) {
        Good good = Good.values()[position];
        inventory.put(good, getInventory(good) - amount);
        ship.setCurCargo(ship.getCurCargo() + amount);
    }

    public Player updatePlayer(int position) {
        return updatePlayer(position, 1);
    }

    private Player updatePlayer(int position, int amount) {
        Good good = Good.values()[position];
        player.upCargo(position, amount);
        player.downCredits(prices, position, amount);
        return player;
    }

    public void upInventory(int position, int amount) {
        Good good = Good.values()[position];
        inventory.put(good, getInventory(good) + amount);
    }

    private String toString(Good good) {
        String returnString = "";
        returnString += String.format("%1$11s", good.toString());
        returnString += String.format("%1$5s", "$" + getPrice(good));
        returnString += String.format("%1$5s", getInventory(good));
        return returnString;
    }

    private void makeList() {
        int i = 0;
        for (Good good: Good.values()) {
            recycleViewList.add(i + " " + toString(good));
            i++;
        }
    }

    public void updateList() {
        List<String> list =  new ArrayList<>(10);
        int i = 0;
        for (Good good: Good.values()) {
            list.add(i + " " + toString(good));
            i++;
        }
        recycleViewList = list;
    }

    public List<Good> getGoods() {
        return Collections.unmodifiableList(goods);
    }

    public List<String> getList() {
        return Collections.unmodifiableList(recycleViewList);
    }

    public EnumMap<Good, Integer> getPrices() { return prices; }
}
