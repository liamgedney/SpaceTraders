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
    private List<Good> goods = new ArrayList<>(10);


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

    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    private int calculateAmount(int techLevel, Good good) {
        int amt = 10 * ( techLevel - good.mtlp() + 1 );
        if (amt < 0) {
            return 0;
        } else {
            return amt;
        }
    }


    public int getPrice(Good good) {
        return prices.get(good);
    }

    public boolean canBuy(Good good, int amount) {
        if (this.techLevel < good.mtlu()) {
            System.out.println("This planet cannot produce this good.");
            return false;
        } else if (player.getCredits() < prices.get(good) * amount) {
            System.out.println("You don't have enough credits to buy this item.");
            return false;
        } else if (ship.getCargoSpace() < amount) {
            //System.out.println("You don't have enough cargo space.");
            //return false;
        } else if (inventory.get(good) < amount) {
            System.out.println("This shop is all out of this good");
            return false;
        }
        return true;
    }

    public void buy(int position) {
        buy(position, 1);
    }

    public int getInventory(Good good) {
        return inventory.get(good);
    }

    public void buy(int position, int amount) {
        Good good = Good.values()[position];
        if (canBuy(good, amount)) {
            inventory.put(good, getInventory(good) - amount);
        }
    }

    public Player updatePlayer(int position) {
        return updatePlayer(position, 1);
    }

    public Player updatePlayer(int position, int amount) {
        Good good = Good.values()[position];
        if (canBuy(good, amount)) {
            player.upCargo(position, amount);
            player.downCredits(prices, position, amount);
        }
        return player;
    }

    public void upInventory(int position, int amount) {
        Good good = Good.values()[position];
        inventory.put(good, getInventory(good) + amount);
    }



    public String toString(Good good) {
        String returnString = "";
        returnString += String.format("%1$11s", good.toString());
        returnString += String.format("%1$5s", "$" + getPrice(good));
        returnString += String.format("%1$5s", getInventory(good));
        return returnString;
    }

    public void makeList() {
        int count = 0;
        for (Good good: Good.values()) {
            recycleViewList.add(count + " " + toString(good));
            count++;
        }
    }

    public void updateList() {
        List<String> list =  new ArrayList<>(10);
        int count = 0;
        for (Good good: Good.values()) {
            list.add(count + " " + toString(good));
            count++;
        }
        recycleViewList = list;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public List<String> getList() {
        return recycleViewList;
    }

    public EnumMap<Good, Integer> getPrices() { return prices; }
}
