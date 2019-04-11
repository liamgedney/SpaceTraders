package edu.gatech.spacetraders.viewmodels;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import edu.gatech.spacetraders.entity.Good;

/**
 * Gets the GameData
 */
public class GameDataInstanceGetter {
    private static GameData gameData;

    /**
     * constructor
     * @param gameData sets gameData
     */
    public void newGameData(GameData gameData) {
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
            GameDataInstanceGetter.setGameData((GameData) in.readObject());
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
            GameData sm = GameDataInstanceGetter.getGameData();
            Log.e("credits", String.valueOf(gameData.getPlayer().getCredits()));
            for (Good x : Good.values()) {
                Log.e("ayylmao", x.toString() + " " + String.valueOf(gameData
                        .getPlayer().getShip().getCargoHold().get(x)));
            }
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(sm);
            out.close();
        } catch (IOException e){
            Log.e("Save", "Error writing an entry from binary file", e);
        }
    }

    /**
     * creates new file
     * @param file the new file
     * @return boolean whether it work
     */
    /*public static boolean newBinary(File file) {
        boolean success = true;
        try {
            GameData sm = new GameData();
            GameDataInstanceGetter.setGameData(gameData);
            Log.e("credits", String.valueOf(gameData.getPlayer().getCredits()));
            for (Good x : Good.values()) {
                Log.e("ayylmao", x.toString() + String.valueOf(gameData.getPlayer()
                        .getShip().getCargoHold().get(x)));
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
