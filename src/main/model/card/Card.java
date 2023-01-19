package main.model.card;

public abstract class Card {
    int value;

    public Card(int value) {
        if (value > 10 || value < 1) throw new RuntimeException("Invalid Card Value");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Card ofString(String s) {
        if (isPipCard(s)) return new PipCard(Integer.parseInt(s));
        if (isFigure(s)) return new FigureCard();
        if (isAce(s)) return new AceCard();
        throw new RuntimeException("Invalid Card Format");
    }

    public boolean isAce() {
        return this instanceof AceCard;
    }

    private static boolean isAce(String card) {
        return card.equals("A") || card.equals("a");
    }

    private static boolean isFigure(String card) {
        return card.equals("J") ||
                card.equals("Q") ||
                card.equals("K") ||
                card.equals("j") ||
                card.equals("q") ||
                card.equals("k");
    }

    private static boolean isPipCard(String card) {
        return card.equals("2") ||
                card.equals("3") ||
                card.equals("4") ||
                card.equals("5") ||
                card.equals("6") ||
                card.equals("7") ||
                card.equals("8") ||
                card.equals("9") ||
                card.equals("10");
    }
}
