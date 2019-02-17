package edu.gatech.spacetraders.entity;

public class Player {

    private String playerName;
    private int points;
    private String difficulty;

    public Player(String playerName, int points, String difficulty) {
        this.playerName = playerName;
        this.points = points;
        this.difficulty = difficulty;
    }

    String getPlayerName() {
        return playerName;
    }

    public int getPoints() {
        return points;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return String.format("Player Name: %s, Points: %d, Difficulty: %s", playerName, points, difficulty);
    }
}
