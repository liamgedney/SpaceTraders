package edu.gatech.spacetraders.entity;

public enum CreditEncounterBad {
    PIRATE();

    private final String st;

    CreditEncounterBad() {
        this.st = "A pirate stole your booty, and you lost : ";
    }

    public String getSt() {
        return st;
    }
}
