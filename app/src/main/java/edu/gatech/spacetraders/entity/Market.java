package edu.gatech.spacetraders.entity;

public class Market {
    private final int NUM_GOODS = 10;
    private int[] prices = new int[NUM_GOODS];

    public Market(int techLevel) {
        int counter = 0;
        for (Good good : Good.values()) {
            prices[counter] = calculatePrice(techLevel, good);
            counter++;
        }
    }

    private int calculatePrice(int techLevel, Good good) {
        return good.base() + good.var() + good.ipl() * ( techLevel - good.mtlp() );
    }

    public int getPrice(int good) {
        return prices[good];
    }
}
