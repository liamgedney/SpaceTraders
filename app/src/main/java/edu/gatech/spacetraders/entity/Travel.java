package edu.gatech.spacetraders.entity;

import android.graphics.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;

//this class takes in list of planets already created from universe,
// takes in ship for fuel and location
//takes in Player
//methods are calculatePlanetsInRange (also updates list for recyclerview),
//method to decrement fuel and update location
//method to  update current planet info(stringbuilder)
//method for random encounters(two types: affect cargo and affect credits(maybe change ship))
public class Travel {
    GameData gameData = GameDataInstanceGetter.getGameData();
    SolarSystem[] systemsArray = gameData.getUniverse().getSystems();
    Point currCoord = gameData.getCurrentSolarSystem().getCoordinates();
    Ship myShip = gameData.getPlayer().getShip();

    public ArrayList<String> getInRangeList() {
        ArrayList<String> stringList = new ArrayList<>(systemsArray.length);
        SolarSystem[] inRange = calculatePlanetsInRange();
        for (int i = 0; i < inRange.length; i++) {
            stringList.add(inRange[i].toString());
        }
        return stringList;
    }


    private SolarSystem[] calculatePlanetsInRange() {
        SolarSystem[] toReturn = new SolarSystem[systemsArray.length];
        int count = 0;
        for (SolarSystem system : systemsArray) {
            if (isInRange(system)) {
                toReturn[count++] = system;
            }
        }
        return toReturn;
    }




    private boolean isInRange(SolarSystem system) {
        if (Math.sqrt((Math.pow(system.getCoordinates().x - currCoord.x, 2)
                + Math.pow(system.getCoordinates().y - currCoord.y, 2))) <= myShip.getCurFuel()) {
            return true;
        }
        return false;
    }

}
