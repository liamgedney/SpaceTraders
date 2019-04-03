package edu.gatech.spacetraders.entity;

import android.graphics.Point;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;
import java.io.Serializable;
import java.util.Random;

//this class takes in list of planets already created from universe,
// takes in ship for fuel and location
//takes in Player
//methods are calculatePlanetsInRange (also updates list for recyclerview),
//method to decrement fuel and update location
//method to  update current planet info(stringbuilder)
//method for random encounters(two types: affect cargo and affect credits(maybe change ship))
public class Travel implements Serializable{
    GameData gameData;
    SolarSystem currSS;
    SolarSystem[] systemsArray;
    Point currCoord;
    Ship myShip;
    Player player;

    public Travel(GameData gameData) {
        this.gameData = gameData;
        currSS = gameData.getCurrentSolarSystem();
        systemsArray = gameData.getUniverse().getSystems();
        currCoord = gameData.getCurrentSolarSystem().getCoordinates();
        myShip = gameData.getPlayer().getShip();
        player = gameData.getPlayer();

    }

    public ArrayList<String> getInRangeList() {
        ArrayList<String> stringList = new ArrayList<>(systemsArray.length);
        SolarSystem[] inRange = calculatePlanetsInRange();
        double range = Math.sqrt((Math.pow(currSS.getCoordinates().x - currCoord.x, 2)
                + Math.pow(currSS.getCoordinates().y - currCoord.y, 2)));
        for (int i = 0; i < inRange.length; i++) {
            stringList.add("Travel Code: " + i + " --> " + inRange[i].toString());
        }
        return stringList;
    }

    public GameData travel(int i) {
        SolarSystem newSS = calculatePlanetsInRange()[i];
        double range = Math.sqrt((Math.pow(newSS.getCoordinates().x - currCoord.x, 2)
                + Math.pow(newSS.getCoordinates().y - currCoord.y, 2)));
        //randomEvent();
        gameData.setCurrentSolarSystem(newSS);
        myShip.setCurFuel((int) (myShip.getCurFuel() - range / 13));
        player.setShip(myShip);
        gameData.setPlayer(player);
        return gameData;
    }

    public void randomEvent() {
        Random random = new Random();
        int encounter = random.nextInt(2);
        if (encounter == 1) {
            int encounterNum = random.nextInt(CreditEncounter.values().length);
            int creditDifference = random.nextInt(player.getCredits() + 1);
            int addSub = random.nextInt(2);
            if (addSub == 0) {
                player.setCredits(player.getCredits() + creditDifference);
            } else {
                player.setCredits(player.getCredits() - creditDifference);
            }
            //Toast.makeText(this, CreditEncounter.values()[encounterNum], Toast.LENGTH_SHORT);

        }
    }

    private SolarSystem[] calculatePlanetsInRange() {
        int numInRange = 0;
        for (SolarSystem system: systemsArray) {
            if (isInRange(system)) {
                numInRange++;
            }
        }
        SolarSystem[] toReturn = new SolarSystem[numInRange];
        int count = 0;
        for (SolarSystem system : systemsArray) {
            if (isInRange(system)) {
                toReturn[count++] = system;
            }
        }
        return toReturn;
    }



    private boolean isInRange(SolarSystem system) {
        double range = Math.sqrt((Math.pow(system.getCoordinates().x - currCoord.x, 2)
                + Math.pow(system.getCoordinates().y - currCoord.y, 2)));
        if (range <= 13 * myShip.getCurFuel() && range > 0) {
            return true;
        }
        return false;
    }

}
