package test;

import main.Game;
import main.model.Croupier;
import main.model.Deck;
import main.model.Player;
import main.model.card.PipCard;
import org.junit.jupiter.api.Test;

import static main.Game.singlePlayerWins;
import static org.junit.jupiter.api.Assertions.*;


public class BlackJackGameTests {

    @Test
    public void playerExceeds() {
        Player player1 = new Player("player1", "6", "Q", "9");
        Croupier croupier = new Croupier("6", "9", "A", "4");
        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerExceeds2() {
        Player player1 = new Player("player1", "7", "A", "5", "K");
        Croupier croupier = new Croupier("3", "5");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void croupierHasMorePointsThanPlayer() {
        Player player1 = new Player("player1", "9", "5", "3");
        Croupier croupier = new Croupier("2", "Q", "2", "6");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void croupierHasMorePointsThanPlayer2() {
        Player player1 = new Player("player1", "Q", "Q");
        Croupier croupier = new Croupier("9", "5", "7");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void croupierHasMorePointsThanPlayer3() {
        Player player1 = new Player("player1", "3", "Q");
        Croupier croupier = new Croupier("A", "6");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void croupierHasMorePointsThanPlayer4() {
        Player player1 = new Player("player1", "3", "Q");
        Croupier croupier = new Croupier("A", "5", "10", "J");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerAndCroupierDraw() {
        Player player1 = new Player("player1", "2", "10", "6", "2");
        Croupier croupier = new Croupier("3", "7", "10");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerHasMorePointsThanCroupier() {
        Player player1 = new Player("player1", "9", "10");
        Croupier croupier = new Croupier("8", "4", "5");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerHasMorePointsThanCroupierAndCroupierHasToAces() {
        Player player1 = new Player("player1", "4", "10", "A", "4");
        Croupier croupier = new Croupier("A", "A", "5", "Q");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerHasLessPointsThanCroupierAndCroupierExceedsValue() {
        Player player1 = new Player("player1", "3", "A", "K");
        Croupier croupier = new Croupier("3", "K", "9");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerHasLessPointsThanCroupierAndCroupierExceedsValue2() {
        Player player1 = new Player("player1", "4", "J", "A", "3");
        Croupier croupier = new Croupier("Q", "6", "10");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerHasLessPointsThanCroupierAndCroupierExceedsValue3() {
        Player player1 = new Player("player1", "J", "6", "2", "2");
        Croupier croupier = new Croupier("2", "10", "A", "Q");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerHasLessPointsThanCroupierAndCroupierExceedsValue4() {
        Player player1 = new Player("player1", "4", "J");
        Croupier croupier = new Croupier("2", "7", "5", "Q");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void onlyPlayerHasBlackJack() {
        Player player1 = new Player("player1", "J", "A");
        Croupier croupier = new Croupier("2", "10", "A", "Q");

        assertTrue(singlePlayerWins(player1, croupier));
    }

    @Test
    public void onlyCroupierHasBlackJack() {
        Player player1 = new Player("player1", "4", "A");
        Croupier croupier = new Croupier("A", "K", "A", "Q");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void onlyCroupierHasBlackJack2() {
        Player player1 = new Player("player1", "4", "A");
        Croupier croupier = new Croupier("10", "A");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void playerAndCroupierHaveBlackJack() {
        Player player1 = new Player("player1", "K", "A");
        Croupier croupier = new Croupier("A", "10");

        assertFalse(singlePlayerWins(player1, croupier));
    }

    @Test
    public void wrongCardFormat() {
        Throwable exception = assertThrows(RuntimeException.class, () -> new Player("player1", "r", "A"));
        assertEquals("Invalid Card Format", exception.getMessage());
    }

    @Test
    public void pipCardHasTooHighValue() {
        Throwable exception = assertThrows(RuntimeException.class, () -> new Deck(new PipCard(11)));
        assertEquals("Invalid Card Value", exception.getMessage());
    }

    @Test
    public void pipCardHasTooLowValue() {
        Throwable exception = assertThrows(RuntimeException.class, () -> new Deck(new PipCard(0)));
        assertEquals("Invalid Card Value", exception.getMessage());
    }

    @Test
    public void croupierHasNotEnoughCardsInDeck() {
        Player player1 = new Player("player1", "9", "2", "4", "4");
        Croupier croupier = new Croupier("2", "7");

        Throwable exception = assertThrows(RuntimeException.class, () -> singlePlayerWins(player1, croupier));
        assertEquals("Croupier has not enough cards in deck", exception.getMessage());
    }

    @Test
    public void croupierHasToLittleInitialCards() {
        Throwable exception = assertThrows(RuntimeException.class, () -> new Croupier(new Deck("3"), new Deck("A", "J")));
        assertEquals("Croupier Must Have Two Initial Cards", exception.getMessage());
    }

    @Test
    public void croupierHasToManyInitialCards() {
        Throwable exception = assertThrows(RuntimeException.class, () -> new Croupier(new Deck("3", "3", "K"), new Deck("A", "J")));
        assertEquals("Croupier Must Have Two Initial Cards", exception.getMessage());
    }

    @Test
    public void caso1() {
        Deck player1 = new Deck("J", "A");
        Deck player2 = new Deck("10", "5", "6");
        Deck player3 = new Deck("3", "6", "A", "3", "A", "K");
        Deck croupier = new Deck("9", "7");
        Deck deck = new Deck( "5", "4", "K", "2");

        assertArrayEquals(new Player[]{new Player("Player 1")}, Game.getWinners(player1, player2, player3, croupier, deck));
    }

    @Test
    public void caso2() {
        Deck player1 = new Deck("10", "K");
        Deck player2 = new Deck("10", "2", "6");
        Deck player3 = new Deck("8", "8", "5");
        Deck croupier = new Deck("5", "10");
        Deck deck = new Deck( "A" , "3" , "K" , "2");

        assertArrayEquals(new Player[]{new Player("Player 1"), new Player("Player 3")}, Game.getWinners(player1, player2, player3, croupier, deck));
    }

    @Test
    public void caso2FirstPlayer() {
        Player player1 = new Player("player1", "10", "K");
        Croupier croupier = new Croupier("5", "10", "A" , "3" , "K" , "2");

        assertTrue(Game.singlePlayerWins(player1, croupier));
    }

    @Test
    public void caso2SecondPlayer() {
        Player player1 = new Player("player2", "10", "2", "6");
        Croupier croupier = new Croupier("5", "10", "A" , "3" , "K" , "2");

        assertFalse(Game.singlePlayerWins(player1, croupier));
    }

    @Test
    public void caso2ThirdPlayer() {
        Player player1 = new Player("player3", "8", "8", "5");
        Croupier croupier = new Croupier("5", "10", "A" , "3" , "K" , "2");

        assertTrue(Game.singlePlayerWins(player1, croupier));
    }
}
