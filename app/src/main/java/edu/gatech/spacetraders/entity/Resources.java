package edu.gatech.spacetraders.entity;
import java.util.Random;

public enum Resources {
    NOSPECIALRESOURCES ("No Special Resources"),
    MINERALRICH ("Mineral Rich"),
    MINERALPOOR ("Mineral Poor"),
    DESERT ("Desert"),
    LOTSOFWATER ("Lots of Water"),
    RICHSOIL ("Rich Soil"),
    POORSOIL ("Poor Soil"),
    RICHFAUNA ("Rich Fauna"),
    LIFELESS ("Lifeless"),
    WEIRDMUSHROOMS ("Weird Mushrooms"),
    LOTSOFHERBS ("Lots of Herbs"),
    ARTISTIC ("Artistic"),
    WARKLIKE ("Warlike");

    private final String name;
    Resources(String name) {
        this.name = name;
    }

    public static Resources getRandom() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    String getName() {return name;}
}
