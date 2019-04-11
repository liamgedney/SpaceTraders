package edu.gatech.spacetraders.entity;

/**
 * enum for good credit encounters
 */
public enum CreditEncounterGood {
    VC("A friendly venture capitalist donated : "),
    PIRATE("A pirate tried to steal your booty, but instead you stole : ");

    private final String st;

    CreditEncounterGood (String str) {
        this.st = str;
    }

    /**
     * returns a string
     * @return string
     */
    public String getSt() {
        return st;
    }
}




