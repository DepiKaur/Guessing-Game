package se.iths.java23.logic;

/**
 * @author Depinder Kaur
 * created on 2024-01-23
 * @version 1.0
 * <p>
 * Player
 * <p>
 * This class contains the player's name and average as its fields.
 */

public class Player {
    private String name;
    private double average;

    public Player(String name, double average) {
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }
}
