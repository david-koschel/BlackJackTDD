package main;

import main.model.Croupier;
import main.model.Deck;
import main.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static boolean singlePlayerWins(Player player, Croupier croupier) {
        boolean result = playerWins(player, croupier);
        croupier.resetCroupierHand();
        return result;
    }

    private static boolean playerWins(Player player, Croupier croupier) {
        int playerHand = player.getHand();
        if (playerHand > 21 || croupier.hasBlackJack()) return false;
        if (player.hasBlackJack()) return true;
        int croupierHand = croupier.getHand();
        while (croupierHand < 17) {
            if (croupierHand > playerHand) return false;
            croupierHand = croupier.incrementAndGetHand();
        }
        if (croupierHand > 21) return true;
        if (croupierHand > playerHand) return false;
        return playerHand > croupierHand;
    }

    public static Player[] getWinners(Deck player1Cards, Deck player2Cards, Deck player3Cards, Deck croupierCards, Deck remainingCards) {
        List<Player> result = new ArrayList<>();

        Player p1 = new Player("Player 1", player1Cards);
        Player p2 = new Player("Player 2", player2Cards);
        Player p3 = new Player("Player 3", player3Cards);
        Croupier croupier = new Croupier(croupierCards, remainingCards);

        if (singlePlayerWins(p1, croupier)) result.add(p1);
        if (singlePlayerWins(p2, croupier)) result.add(p2);
        if (singlePlayerWins(p3, croupier)) result.add(p3);

        return result.toArray(new Player[0]);
    }
}
