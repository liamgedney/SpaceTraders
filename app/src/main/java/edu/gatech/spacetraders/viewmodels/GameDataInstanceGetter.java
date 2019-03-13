package edu.gatech.spacetraders.viewmodels;

public class GameDataInstanceGetter {
    static GameData gameData;
    public void newGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public static GameData getGameData() {
        return gameData;
    }

}
