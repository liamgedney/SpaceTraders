package edu.gatech.spacetraders.viewmodels;

import android.support.annotation.Nullable;

import java.io.Serializable;

import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.entity.Universe;

/**
 * This is our weird singleton/instance type thing
 */
public class GameData implements Serializable {
    @Nullable
    private Player player;
    @Nullable
    private SolarSystem currentSolarSystem;
    @Nullable
    private Universe universe;

    /**
     * null constructor for GameData
     */
    public GameData() {
        this.player = null;
        this.currentSolarSystem = null;
        this.universe = null;
    }

    /**
     * makes it a current solar system
     * @param currentSolarSystem the current one
     */
    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }

    /**
     * makes a player
     * @param player the player we made
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * getter for player
     * @return Player the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * getter for currSolarSystem;
     * @return SolarSystem the system of solar
     */
    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    /**
     * setter for universe
     * @param universe the planet stuff
     */
    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    /**
     * getter for universe
     * @return Universe the uni
     */
    public Universe getUniverse() {
        return universe;
    }

}
