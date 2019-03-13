package edu.gatech.spacetraders.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.Map;
import java.util.Set;
>>>>>>> clair

public class Market {
    private final int NUM_GOODS = 10;
    private int techLevel;
    private EnumMap<Good, Integer> prices = new EnumMap<>(Good.class);
    private EnumMap<Good, Integer> inventory = new EnumMap<>(Good.class);
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
            inventory.put(good, calculateAmount(techLevel, good));
        }
        this.inventory = new int[] {10, 10 , 10, 10, 10, 10, 10, 10, 10, 10};
        this.recycleViewList = new ArrayList<>(10);
        makeList();
    }

    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    private int calculateAmount(int techLevel, Good good) {
        return 10 * ( techLevel - good.mtlp() + 1 );
    }

    public int getPrice(int good) {
        return prices.get(good);
    }

<<<<<<< HEAD
    public boolean canBuy(Good good) {
        if (this.techLevel >= good.mtlu()) {
            if (player.getCredits() >= prices.get(good)) {
                return true;
            }
=======
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
    public boolean buy(Good good, int amount) {
        if (canBuy(good, amount)) {
            inventory.put(good, inventory.get(good) - amount);
            return true;
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
