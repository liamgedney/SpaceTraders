package edu.gatech.spacetraders.viewmodels;

import android.support.annotation.Nullable;

import java.io.Serializable;

import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.SolarSystem;
import edu.gatech.spacetraders.entity.Universe;

public class GameData implements Serializable {
    @Nullable
    private Player player;
    @Nullable
    private SolarSystem currentSolarSystem;
    @Nullable
    private Universe universe;

    public GameData() {
        this.player = null;
        this.currentSolarSystem = null;
        this.universe = null;
    }

    public void setCurrentSolarSystem(SolarSystem currentSolarSystem) {
        this.currentSolarSystem = currentSolarSystem;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public SolarSystem getCurrentSolarSystem() {
        return currentSolarSystem;
    }

    public void setUniverse(Universe universe) {
        this.universe = universe;
    }

    public Universe getUniverse() {
        return universe;
    }

}
