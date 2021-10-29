package sk.uniba.fmph.dcs;

import java.util.*;

public class Deck {

    private final LinkedList<CardInterface> deckOfCards;
    private final DiscardPile discardPile;

    public Deck(DiscardPile discardPile) {
        deckOfCards = new LinkedList<>(initDeck());
        shuffle();
        this.discardPile = discardPile;
    }

    public Deck(DiscardPile discardPile, List<CardInterface> deckOfCards) {
        this.discardPile = discardPile;
        this.deckOfCards = new LinkedList<>(deckOfCards);
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    public List<CardInterface> draw(Integer count) {
        Integer cardCount = count;
        if (cardCount > deckOfCards.size()) {
            deckOfCards.addAll(discardPile.shuffle());
        }
        if (cardCount > deckOfCards.size()) {
            cardCount = deckOfCards.size();
        }
        ArrayList<CardInterface> returnList = new ArrayList<>();
        while (cardCount --> 0) {
            returnList.add(deckOfCards.removeFirst());
        }
        return Collections.unmodifiableList(returnList);
    }

    public LinkedList<CardInterface> getDeckOfCards() {
        return deckOfCards;
    }

    public List<CardInterface> initDeck() {
        LinkedList<CardInterface> deck = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            deck.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }
        for (int j = 0; j < 7; j++) {
            deck.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }
        return deck;
    }
}
