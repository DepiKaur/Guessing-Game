//Depinder Kaur, 2024-01-23, depinder.kaur@iths.se

package se.iths.java23.logic;

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
