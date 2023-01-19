package main.model;


public class Croupier extends Player {

    private final Deck initialDeck;
    private final Deck initialCards;
    private final int initialHand;
    private Deck deck;

    public Croupier(Deck cards, Deck deck) {
        super("Croupier", new Deck(cards));
        if (cards.size() != 2) throw new RuntimeException("Croupier Must Have Two Initial Cards");
        this.initialCards = new Deck(cards);
        this.initialDeck = new Deck(deck);
        this.deck = new Deck(initialDeck);
        initialHand = super.getHand();
    }

    public Croupier(String firstCard, String secondCard, String... deck) {
        this(new Deck(firstCard, secondCard), new Deck(deck));
    }

    @Override
    public int getHand() {
        return initialHand;
    }

    public int incrementAndGetHand() {
        if (!deck.isEmpty()) {
            cards.addCard(deck.takeFirstCard());
        } else {
            throw new RuntimeException("Croupier has not enough cards in deck");
        }
        return super.getHand();
    }

    public void resetCroupierHand() {
        this.deck = new Deck(initialDeck);
        this.cards = new Deck(initialCards);
    }
}
