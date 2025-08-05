package org.schoolsorokin.javacore.oop.games;

public class GameScore {

    private static int totalScore = 0;

    public static int getTotalScore() {
        return totalScore;
    }

    public static void addPoints(int points) {
        totalScore += points;
    }

}

class Player {

    private String name;
    private int personalScore;

    public Player(String name, int points) {
        this.name = name;
        this.personalScore = points;
        GameScore.addPoints(points);
    }

    public String getName() {
        return name;
    }

    public int getPersonalScore() {
        return personalScore;
    }
}
