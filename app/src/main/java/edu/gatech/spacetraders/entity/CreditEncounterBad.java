package edu.gatech.spacetraders.entity;

/**
 * enum for bad credit encounter
 */
public enum CreditEncounterBad {
    PIRATE();

    private final String st;

    CreditEncounterBad() {
        this.st = "A pirate stole your booty, and you lost : ";
    }

    /**
     * returns the string??
     * @return a string
     */
    public String getSt() {
        return st;
    }
}
