package edu.gatech.spacetraders.entity;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Market {
    private final int NUM_GOODS = 10;
    private int techLevel;
    private EnumMap<Good, Integer> prices = new EnumMap<Good, Integer>(Good.class);
    private Player player;
    private Ship ship;
    private int[] inventory;
    private List<String> recycleViewList;

    public Market(int techLevel, Player player, Ship ship) {
        this.techLevel = techLevel;
        this.player = player;
        this.ship = ship;
        for (Good good : Good.values()) {
            prices.put(good, calculatePrice(techLevel, good));
        }
        this.inventory = new int[] {10, 10 , 10, 10, 10, 10, 10, 10, 10, 10};
        this.recycleViewList = new ArrayList<>(10);
        makeList();
    }

    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    public int getPrice(int good) {
        return prices.get(good);
    }

    public boolean canBuy(Good good) {
        if (this.techLevel >= good.mtlu()) {
            if (player.getCredits() >= prices.get(good)) {
                return true;
            }
        }
        return false;
    }

    public int getInventory(int index) {
        return inventory[index];
    }

    public String toString(int index) {
        String returnString = "";
        Good good = Good.values()[index];
        returnString += good;
        returnString += "   ";
        returnString += prices.get(good);
        returnString += "   ";
        returnString += getInventory(index);
        return returnString;
    }

    public void makeList() {
        for (int i = 0; i < 9; i++) {
            recycleViewList.add(toString(i));
        }
    }

    public List<String> getList() {
        return recycleViewList;
    }
}
