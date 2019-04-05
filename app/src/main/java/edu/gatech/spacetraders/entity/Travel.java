package edu.gatech.spacetraders.entity;

import android.graphics.Point;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.gatech.spacetraders.viewmodels.GameData;
import edu.gatech.spacetraders.viewmodels.GameDataInstanceGetter;
import java.io.Serializable;
import java.util.EnumMap;
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
        gameData.setCurrentSolarSystem(newSS);
        //myShip.setCurFuel((int) (myShip.getCurFuel() - range / 13));
        player.setShip(myShip);
        gameData.setPlayer(player);
        return gameData;
    }

    public String randomEvent() {
        Random random = new Random();
        int encounter = random.nextInt(11);
        String display;
        if (encounter == 0) {
            //safe
            display = "You made it safely";
        } else if (encounter == 1) {
            //credit stuff
            int creditDifference = random.nextInt(player.getCredits() + 1);
            int addSub = random.nextInt(2);
            int encounterNum;
            if (addSub == 0) {
                encounterNum = random.nextInt(CreditEncounterGood.values().length);
                player.setCredits(player.getCredits() + creditDifference);
                display = CreditEncounterGood.values()[encounterNum].getSt() + creditDifference + " credits. UwU";
                gameData.setPlayer(player);
            } else {
                encounterNum = random.nextInt(CreditEncounterBad.values().length);
                if (creditDifference > player.getCredits()) {
                    player.setCredits(0);
                } else {
                    player.setCredits(player.getCredits() - creditDifference);
                }
                gameData.setPlayer(player);
                display = CreditEncounterBad.values()[encounterNum].getSt() + creditDifference + " credits. :'(";
            }
        } else if (encounter == 2) {
            //cargo
            int cargoToChange = random.nextInt(10);
            Good cargoUpdate = Good.values()[cargoToChange];
            int addRem = random.nextInt(7);
            if (addRem == 0) {
                //good outcome
                EnumMap<Good, Integer> cargoHold = myShip.getCargoHold();
                int currAmount = cargoHold.get(cargoUpdate);
                int amount = currAmount + random.nextInt(currAmount + 1) + 10;
                int total = amount > myShip.getMaxCargo() ? myShip.getMaxCargo() : amount;
                cargoHold.put(cargoUpdate, total);
                display = "You ran into a nice Pirate, and he tried to steal but had enough emotional intelligence to realize " +
                        "this is not a good decision and gave you: " + total + " "
                        + cargoUpdate + "s <3";
            } else {
                EnumMap<Good, Integer> cargoHold = myShip.getCargoHold();
                int currAmount = cargoHold.get(cargoUpdate);
                int amount = currAmount - random.nextInt(currAmount + 1) - 10;
                int total = amount < 0 ? 0 : amount;
                cargoHold.put(cargoUpdate, total);
                display = "You ran into a mean Pirate, and he stole: " + total + " "
                        + cargoUpdate + "s </3";
            }
        } else if (encounter == 3) {
            //pilot
            player.setPilotPoints(player.getPilotPoints() + 1);
            display = "You meet a new skilled Pilot, and he increased your pilot points by 1";
        } else if (encounter == 4) {
            //pilot
            player.setFighterPoints(player.getFighterPoints() + 1);
            display = "You meet a new skilled Warrior, and he increased your fighter points by 1";
        } else if (encounter == 5) {
            //pilot
            player.setTraderPoints(player.getTraderPoints() + 1);
            display = "You meet a new skilled trader, and he increased your trader points by 1";
        } else if (encounter == 6) {
            player.setEngineerPoints(player.getEngineerPoints() + 1);
            display = "You meet a new skilled engineer, and he increased your engineer points by 1";
        } else if (encounter == 7) {
            //pilot
            player.setPilotPoints(player.getPilotPoints() - 1);
            display = "Your pilot quits! Your pilot points decrease by 1";
        } else if (encounter == 8) {
            //pilot
            player.setFighterPoints(player.getFighterPoints() - 1);
            display = "Your fighter quits! Your fighter points decrease by 1";
        } else if (encounter == 9) {
            //pilot
            player.setTraderPoints(player.getTraderPoints() - 1);
            display = "Your trader is convicted of insider trading! Your trading points decreased by 1";
        } else if (encounter == 10) {
            player.setEngineerPoints(player.getEngineerPoints() - 1);
            display = "Your engineer is replaced by a UGA grad! Your engineering points decreased by 1";
        } else {
            display = "temp";
        }
        return display;
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
