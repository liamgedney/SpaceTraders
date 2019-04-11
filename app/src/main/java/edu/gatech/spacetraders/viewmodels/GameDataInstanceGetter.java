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

public class GameDataInstanceGetter {
    private static GameData gameData;
    public void newGameData(GameData gameData) {
        this.gameData = gameData;
    }
    private static void setGameData(GameData gameData2){
        gameData = gameData2;
    }

    public static GameData getGameData() {
        return gameData;
    }

    public static void loadBinary(File file){
        boolean success = true;
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            GameDataInstanceGetter.setGameData((GameData) in.readObject());
            in.close();
        } catch (IOException e) {
            Log.e("Load", "Error reading an entry from binary file", e);
            success = false;
        } catch (ClassNotFoundException e){
            Log.e("Load", "Error reading an entry from binary file", e);
            success = false;
        }
    }
    public static void saveBinary(File file) {
        boolean success = true;
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
            success = false;
        }
    }
    public static boolean newBinary(File file) {
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
    }

}
