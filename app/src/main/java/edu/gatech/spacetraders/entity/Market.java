package edu.gatech.spacetraders.entity;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

public class Market {
    private int techLevel;
    //use an enum map to track prices and amount in stock of each good
    private EnumMap<Good, Integer> prices = new EnumMap<>(Good.class);
    private EnumMap<Good, Integer> inventory = new EnumMap<>(Good.class);
    private Player player;
    private Ship ship;
    private List<String> recycleViewList = new ArrayList<>(10);

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
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    /**
     * calculates the amount of starting inventory for given good using:
     *
     * @param techLevel the tech level of the current system
     * @param good the good we are calculating price for
     * @return the amount
     */
    private int calculateAmount(int techLevel, Good good) {
        return 10 * ( techLevel - good.mtlp() + 1 );
    }

    public int getPrice(Good good) {
        return prices.get(good);
    }

    /**
     * helper method determines if the player has enough money and space to
     * buy, and the system market has enough of the good to sell, the given
     * amount of the given good.
     *
     * @param good the good to check
     * @param amount the amount of the good we want to buy
     * @return if we can buy it or not
     */
    public boolean canBuy(Good good, int amount) {
        if (this.techLevel < good.mtlu()) {
            System.out.println("not enough techlevel to produce good");
            return false;
        } else if (player.getCredits() < prices.get(good) * amount) {
            System.out.println("not enough credits");
            return false;
        } else if (ship.getCargoSpace() < amount) {
            System.out.println("not enough cargo space");
            return false;
        } else if (inventory.get(good) < amount) {
            System.out.println("not enough in shop inventory");
            return false;
        } else {
            System.out.println("success!");
            return true;
        }
    }

    public boolean buy(Good good) {
        return buy(good, 1);
    }

    /**
     * buys the given amount of the given good if that's possible
     * (checked by canBuy method)
     *
     * @param good the good to buy
     * @param amount the amount we want
     * @return if we bought it or not
     */
    public boolean buy(Good good, int amount) {
        if (canBuy(good, amount)) {
            inventory.put(good, getInventory(good) - amount);
            return true;
        }
        return false;
    }

    public int getInventory(Good good) {
        return inventory.get(good);
    }

    /**
     * outputs a string in the format
     * GOOD   Price   Stock
     *
     * @param good the good to report
     * @return the string
     */
    public String toString(Good good) {
        String returnString = "";
        returnString += good.toString();
        returnString += "   ";
        returnString += getPrice(good);
        returnString += "   ";
        returnString += getInventory(good);
        return returnString;
    }

    public void makeList() {
        for (Good good: Good.values()) {
            recycleViewList.add(toString(good));
        }
    }

    public List<String> getList() {
        return recycleViewList;
    }
}
