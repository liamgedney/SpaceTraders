package edu.gatech.spacetraders.viewmodels;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.Objects;

import edu.gatech.spacetraders.entity.Good;
import edu.gatech.spacetraders.entity.Player;
import edu.gatech.spacetraders.entity.Ship;

/**
 * Gets the GameData
 */
public class GameDataInstanceGetter {
    private static GameData gameData;

    /**
     * constructor
     * @param gameData sets gameData
     */
    public static void newGameData(GameData gameData) {
        GameDataInstanceGetter.gameData = gameData;
    }

    /**
     * other constructor
     * @param gameData2 for some reason
     */
    private static void setGameData(GameData gameData2){
        gameData = gameData2;
    }

    /**
     * gameData getter
     * @return GameData
     */
    public static GameData getGameData() {
        return gameData;
    }

    /**
     * loads the file after it was saved
     * @param file in android
     */
    public static void loadBinary(File file){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            setGameData((GameData) in.readObject());
            in.close();
        } catch (IOException e) {
            Log.e("Load", "Error reading an entry from binary file", e);
        } catch (ClassNotFoundException e){
            Log.e("Load", "Error reading an entry from binary file", e);
        }
    }

    /**
     * saves the binary file
     * @param file on android
     */
    public static void saveBinary(File file) {
        try {
            GameData sm = getGameData();
            Player player = gameData.getPlayer();
            Ship ship = Objects.requireNonNull(player).getShip();
            AbstractMap<Good, Integer> map = ship.getCargoHold();
            Log.e("credits", String.valueOf(player.getCredits()));
            for (Good x : Good.values()) {
                Log.e("ayylmao", x.toString() + " " + String.valueOf(map.get(x)));
            }
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(sm);
            out.close();
        } catch (IOException e){
            Log.e("Save", "Error writing an entry from binary file", e);
        }
    }


    /*public static boolean newBinary(File file) {
        boolean success = true;
        try {
            GameData sm = new GameData();
            setGameData(gameData);
            Player player = gameData.getPlayer();
            Ship ship = player.getShip();
            EnumMap<Good, Integer> map = ship.getCargoHold();
            Log.e("credits", String.valueOf(player.getCredits()));
            for (Good x : Good.values()) {
                Log.e("ayylmao", x.toString() + String.valueOf(map.get(x)));
            }
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(sm);
            out.close();
        } catch (IOException e){
            Log.e("Save", "Error writing an entry from binary file", e);
            success = false;
        }
        return success;
    }*/

}
