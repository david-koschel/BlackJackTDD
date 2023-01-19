package main.model;

import main.model.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {

    List<Card> cards;

    public Deck(Card... cards) {
        this.cards = List.of(cards);
    }

    public Deck(Deck deck) {
        cards = new ArrayList<>(deck.cards);
    }

    public Deck(String... cards) {
        this.cards = new ArrayList<>();
        Arrays.stream(cards).forEach(card -> {
            this.cards.add(Card.ofString(card));
        });
    }

    public int size() {
        return cards.size();
    }

    public int totalSumWithAceValueAs1() {
        return cards.stream().reduce(0, (suma, card) -> suma + card.getValue(), Integer::sum);
    }

    public boolean hasAces() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public Card takeFirstCard() {
        return cards.remove(0);
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
