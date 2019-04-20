package edu.gatech.spacetraders.entity;

import java.util.ArrayList;

import edu.gatech.spacetraders.viewmodels.GameData;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Travel class
 * this class takes in list of planets already created from universe,
 * takes in ship for fuel and location
 * takes in Player
 * methods are calculatePlanetsInRange (also updates list for recyclerview),
 * method to decrement fuel and update location
 * method to  update current planet info(stringbuilder)
 * method for random encounters(two types: affect cargo and affect credits(maybe change ship))
 */
public class Travel implements Serializable{
    int THIRTEEN = 13;
    int RANDOMNUM = 11;
    private final GameData gameData;
    private final SolarSystem[] systemsArray;
    private final Point2 currCoord;
    private final Ship myShip;
    private final Player player;

    /**
     * Constructor method
     * @param gameData current game's data
     */
    public Travel(GameData gameData) {
        this.gameData = gameData;
        SolarSystem currSS = gameData.getCurrentSolarSystem();
        Universe uni = gameData.getUniverse();
        systemsArray = Objects.requireNonNull(uni).getSystems();
        currCoord = Objects.requireNonNull(currSS).getCoordinates();
        player = gameData.getPlayer();
        myShip = Objects.requireNonNull(player).getShip();
    }

    /**
     * Returns list of planets in range
     * @return list of planets in range
     */
    public List<String> getInRangeList() {
        List<String> stringList = new ArrayList<>(systemsArray.length);
        SolarSystem[] inRange = calculatePlanetsInRange();
        //double range = Math.sqrt((Math.pow(currSS.getCoordinates().x - currCoord.x, 2)
        //        + Math.pow(currSS.getCoordinates().y - currCoord.y, 2)));
        for (int i = 0; i < inRange.length; i++) {
            stringList.add("Travel Code: " + i + " --> " + inRange[i].toString());
        }
        return stringList;
    }

    /**
     * The method called when traveling
     * @param i the index of the planet in range selected
     * @return the data of the game
     */
    public GameData travel(int i) {
        SolarSystem newSS = calculatePlanetsInRange()[i];
        double range = Math.sqrt((Math.pow(newSS.getCoordinates().x - currCoord.x, 2)
                + Math.pow(newSS.getCoordinates().y - currCoord.y, 2)));
        gameData.setCurrentSolarSystem(newSS);

        myShip.setCurFuel((int) (myShip.getCurFuel() - (range / THIRTEEN)));
        player.setShip(myShip);
        gameData.setPlayer(player);
        return gameData;
    }

    /**
     * Creates a random event
     * @return string message of random event occurence
     */
    public String randomEvent() {

        Random random = new Random();

        int encounter = random.nextInt(RANDOMNUM);
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
                display = CreditEncounterGood.values()[encounterNum].getSt() + creditDifference
                        + " credits. UwU";
                gameData.setPlayer(player);
            } else {
                encounterNum = random.nextInt(CreditEncounterBad.values().length);
                if (creditDifference > player.getCredits()) {
                    player.setCredits(0);
                } else {
                    player.setCredits(player.getCredits() - creditDifference);
                }
                gameData.setPlayer(player);
                display = CreditEncounterBad.values()[encounterNum].getSt() + creditDifference
                        + " credits. :'(";
            }
        } else if (encounter == 2) {
            display = randomEvent3();
        } else {
            display = randomEvent2(encounter);
        }
        return display;
    }

    /**
     * continuation of randomEvent()
     * @param encounter the random number generated in randomEvent()
     * @return a String describing what happened.
     */
    private String randomEvent2(int encounter) {
        String display;
        if (encounter == 3) {
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
            display = "Your trader is convicted of insider trading! " +
                    "Your trading points decreased by 1";
        } else if (encounter == 10) {
            player.setEngineerPoints(player.getEngineerPoints() - 1);
            display = "Your engineer is replaced by a UGA grad! " +
                    "Your engineering points decreased by 1";
        } else {
            display = "temp";
        }
        return display;
    }

    /**
     * continuation of randomEvent()
     * @return a String describing what happened.
     */
    private String randomEvent3() {
        String display;
        Random random = new Random();
        //cargo
        int cargoToChange = random.nextInt(10);
        Good cargoUpdate = Good.values()[cargoToChange];
        int addRem = random.nextInt(7);
        if (addRem == 0) {
            //good outcome
            EnumMap<Good, Integer> cargoHold = myShip.getCargoHold();
            int currAmount;
            if (cargoUpdate == null) {
                throw new NullPointerException("cargoUpdate cannot be null.");
            } else {
                currAmount = Objects.requireNonNull(cargoHold.get(cargoUpdate));
            }
            int amount = currAmount + random.nextInt(currAmount + 1) + 10;
            int total = (amount > myShip.getMaxCargo()) ? myShip.getMaxCargo() : amount;
            cargoHold.put(cargoUpdate, total);
            display = "You ran into a nice Pirate, and he tried to steal but had enough" +
                    " emotional intelligence to realize " +
                    "this is not a good decision and gave you: " + total + " "
                    + cargoUpdate + "s <3";
        } else {
            EnumMap<Good, Integer> cargoHold = myShip.getCargoHold();
            int currAmount;
            if (cargoUpdate == null) {
                throw new NullPointerException("cargoUpdate cannot be null.");
                } else {
                currAmount = Objects.requireNonNull(cargoHold.get(cargoUpdate));
            }
            int amount = currAmount - random.nextInt(currAmount + 1) - 10;
            int total = (amount < 0) ? 0 : amount;
            cargoHold.put(cargoUpdate, total);
            display = "You ran into a mean Pirate, and he stole: " + total + " "
                    + cargoUpdate + "s </3";
        }
        return display;
    }

    /**
     * Calculates what planets are in range
     * @return array of solar systems in range
     */
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
                toReturn[count] = system;
                count++;
            }
        }
        return toReturn;
    }


    /**
     * Deteremines whether a planet is in range
     * @param system the planet being looked at
     * @return boolean value of whether a planet is in range
     */
    private boolean isInRange(SolarSystem system) {
        double range = Math.sqrt((Math.pow(system.getCoordinates().x - currCoord.x, 2)
                + Math.pow(system.getCoordinates().y - currCoord.y, 2)));
        int CURFUELNUM = 13;
        return (range <= (CURFUELNUM * myShip.getCurFuel())) && (range > 0);
    }

}
