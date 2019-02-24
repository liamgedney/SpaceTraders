package edu.gatech.spacetraders.entity;

import java.util.ArrayList;

public class Universe {
    private ArrayList<String> SolarSystemArr = new ArrayList<>();

    public void createSolarSystems() {
        for (int i = 0; i < 10; i++) {
            SolarSystem temp = new SolarSystem("Planet" + i, 0, 0);
            SolarSystemArr.add(temp.toString());
        }
    }

    public ArrayList<String> getSolarSystemArr() {
        return SolarSystemArr;
    }
}