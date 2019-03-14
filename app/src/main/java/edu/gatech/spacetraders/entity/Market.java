package edu.gatech.spacetraders.entity;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

public class Market {
    private int techLevel;
    private EnumMap<Good, Integer> prices = new EnumMap<>(Good.class);
    private EnumMap<Good, Integer> inventory = new EnumMap<>(Good.class);
    private Player player;
    private Ship ship;
    private List<String> recycleViewList = new ArrayList<>(10);

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

    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    private int calculateAmount(int techLevel, Good good) {
        return 10 * ( techLevel - good.mtlp() + 1 );
    }

    public int getPrice(Good good) {
        return prices.get(good);
    }

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
            inventory.put(good, getInventory(good) - amount);
            return true;
        }
        return false;
    }

    public int getInventory(Good good) {
        return inventory.get(good);
    }

    public String toString(Good good) {
        String returnString = "";
        returnString += String.format("%1$5s", good.toString());
        returnString += String.format("%1$5s", getPrice(good));
        returnString += String.format("%1$5s", getInventory(good));
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
