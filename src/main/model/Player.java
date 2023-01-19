package main.model;

import java.util.Objects;

public class Player {

    String name;
    Deck cards;

    public Player(String name, Deck deck) {
        this.name = name;
        this.cards = deck;
    }

    public Player(String name, String... cards) {
        this.name = name;
        this.cards = new Deck(cards);
    }

    public boolean hasBlackJack() {
        return cards.size() == 2 && getHand() == 21;
    }

    public int getHand() {
        int sum = cards.totalSumWithAceValueAs1();
        if (cards.hasAces() && sum <= 11) {
            sum += 10;
        }
        return sum;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }
}
