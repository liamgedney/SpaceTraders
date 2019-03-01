package edu.gatech.spacetraders.entity;

import java.util.Random;

public enum Good {
    //good      base    var     ipl     mtlp    mtlu
    WATER       (30,    4,      3,      0,      0),
    FURS        (250,   10,     10,     0,      0),
    FOOD        (100,   5,      5,      1,      0),
    ORE         (350,   10,     20,     2,      2),
    GAMES       (250,   5,      -10,    3,      1),
    FIREARMS    (1250,  100,    -75,    3,      1),
    MEDICINE    (650,   10,     -20,    4,      1),
    MACHINES    (900,   5,      -30,    4,      3),
    NARCOTICS   (3500,  150,    -125,   5,      0),
    ROBOTS      (5000,  100,    -150,   6,      4);

    private final int base; //base price of good
    private final int var; //variance in price
    private final int ipl; //increase in price per tech level
    private final int mtlp; //minimum tech level to produce good
    private final int mtlu; //minimum tl to buy/use good
    Random rand = new Random();

    Good(int base, int var, int ipl, int mtlp, int mtlu) {
        this.base = base;
        this.var = var;
        this.ipl = ipl;
        this.mtlp = mtlp;
        this.mtlu = mtlu;
    }
    public int base() { return base; }
    public int var() {
        int coin = rand.nextInt(2) * 2 - 1;
        return rand.nextInt(var) * coin;
    }
    public int ipl() { return ipl; }
    public int mtlp() { return mtlp; }
    public int mtlu() { return mtlu; }
}
