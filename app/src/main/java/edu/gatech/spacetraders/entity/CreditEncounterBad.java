package edu.gatech.spacetraders.entity;

public enum CreditEncounterBad {
    PIRATE("A pirate stole your booty, and you lost : ");

    private final String st;

    CreditEncounterBad (String str) {
        this.st = str;
    }

    public String getSt() {
        return st;
    }
}
