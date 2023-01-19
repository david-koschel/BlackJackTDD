package main;

import main.model.Croupier;
import main.model.Player;

import static main.Game.singlePlayerWins;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player("player1", "6", "5", "a", "8");
        Croupier croupier = new Croupier("5", "8", "j", "l");

        System.out.println(singlePlayerWins(player1, croupier));
    }
}