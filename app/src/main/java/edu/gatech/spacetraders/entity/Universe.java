package edu.gatech.spacetraders.entity;

import java.util.ArrayList;

public class Universe {
    private static int NUM_SYSTEMS = 10;
    private SolarSystem[] systems = new SolarSystem[NUM_SYSTEMS];

    public void createSolarSystems() {
        for (int i = 0; i < NUM_SYSTEMS; i++) {
            systems[i] = new SolarSystem("Planet" + i, 0, 0);
        }
    }

    public String toString() {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < NUM_SYSTEMS; i++) {
            answer.append(systems[i].toString());
            answer.append("/n");
        }
        return answer.toString();
    }
    /*
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
    */
}