package edu.gatech.spacetraders.entity;

import java.util.EnumMap;

public class Market {
    private final int NUM_GOODS = 10;
    private int techLevel;
    private EnumMap<Good, Integer> prices = new EnumMap<Good, Integer>(Good.class);
    private Player player;
    private Ship ship;

    public Market(int techLevel, Player player, Ship ship) {
        this.techLevel = techLevel;
        this.player = player;
        this.ship = ship;
        for (Good good : Good.values()) {
            if (isSoldHere(good)) {
                prices.put(good, calculatePrice(techLevel, good));
            }
        }
    }

    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    public int getPrice(int good) {
        return prices.get(good);
    }

    public boolean isSoldHere(Good good) {
        return this.techLevel >= good.mtlu();
    }



    public boolean canBuy(Good good) {
        if (this.techLevel >= good.mtlu()) {
            if (player.getCredits() >= prices.get(good)) {
                return true;
            }
        }
        return false;
    }
}
