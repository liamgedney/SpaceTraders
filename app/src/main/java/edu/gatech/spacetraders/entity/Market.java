package edu.gatech.spacetraders.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

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
    //GameData gameData = GameDataInstanceGetter.getGameData();


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
           // Collection<Good> goods = new ArrayList<>(10);
            // goods.add(good);
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


    /**
     * gets price
     * @param good good
     * @return int good
     */
    public int getPrice(Good good) {
        return prices.get(good);
    }


    /**
     * helper method determines if the player has enough money and space to
     * buy, and the system market has enough of the good to sell, the given
     * amount of the given good.
     *
     * @param position the good to check
     * @return if we can buy it or not
     */
    public boolean canBuy(int position) {
        Good good = Good.values()[position];
        if (this.techLevel < good.mtlu()) {
            Log.d("","This planet cannot produce this good.");
            return false;
        } else if (player.getCredits() < (prices.get(good))) {
            Log.d("","You don't have enough credits to buy this item.");
            return false;
        } else if (ship.getCargoSpace() == 0) {
            Log.d("","You don't have enough cargo space.");
            return false;
        } else if (inventory.get(good) < 1) {
            Log.d("","This shop is all out of this good");
            return false;
        }
        return true;
    }

    /**
     * buy it
     * @param position good
     */
    public void buy(int position) {
        Good good = Good.values()[position];
        inventory.put(good, getInventory(good) - 1);
        ship.setCurCargo(ship.getCurCargo() + 1);
    }

    private int getInventory(Good good) {
        return inventory.get(good);
    }

    /**
     * update player
     * @param position with good
     * @return Player
     */
    public Player updatePlayer(int position) {
        return updatePlayer(position, 1);
    }

    /**
     * update the player
     * @param position good
     * @param amount amount
     * @return Player player
     */
    private Player updatePlayer(int position, int amount) {
       // Good good = Good.values()[position];
        player.upCargo(position, amount);
        player.downCredits(prices, position, amount);
        return player;
    }

    /**
     * increase inventory
     * @param position good
     * @param amount money
     */
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

    /**
     * updates the List
     */
    public void updateList() {
        List<String> list =  new ArrayList<>(10);
        int i = 0;
        for (Good good: Good.values()) {
            list.add(i + " " + toString(good));
            i++;
        }
        recycleViewList = list;
    }


    /*public List<Good> getGoods() {
        return goods;
    }*/

    /**
     * getter for list
     * @return List<String>
     */
    public List<String> getList() {
        return recycleViewList;
    }

    /**
     * get prices
     * @return EnumMap<Good, Integer>
     */
    public EnumMap<Good, Integer> getPrices() { return prices; }
}
