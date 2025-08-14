package org.schoolsorokin.javacore.oop.games;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player34", 100);
        Player player2 = new Player("Player12", 25);
        Player player3 = new Player("Player666", 150);

        System.out.println("Игрок: " + player1.getName() + " Личный счет: " + player1.getPersonalScore());
        System.out.println("Игрок: " + player2.getName() + " Личный счет: " + player2.getPersonalScore());
        System.out.println("Игрок: " + player3.getName() + " Личный счет: " + player3.getPersonalScore());

        System.out.println("Счет игры: " + GameScore.getTotalScore());
    }
}
