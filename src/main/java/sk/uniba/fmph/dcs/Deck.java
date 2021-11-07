package sk.uniba.fmph.dcs;

import java.util.*;

public class Deck implements DeckInterface {

    private final LinkedList<CardInterface> deckOfCards;
    private final DiscardPileInterface discardPile;
    private final Shuffler myShuffler;
    public Deck(DiscardPileInterface discardPile) {
        myShuffler = new Shuffler(initDeck());
        deckOfCards = new LinkedList<>(myShuffler.shuffle());
        this.discardPile = discardPile;
    }

    public Deck(DiscardPileInterface discardPile, List<CardInterface> deckOfCards) {
        this.discardPile = discardPile;
        myShuffler = new Shuffler(deckOfCards);
        this.deckOfCards = new LinkedList<>(myShuffler.shuffle());
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    public List<CardInterface> draw(int count) {
        int cardCount = count;
        if (cardCount > deckOfCards.size()) {
            deckOfCards.addAll(discardPile.getShuffledCards());
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

    public List<CardInterface> getDeckOfCards() {
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

    public int getPoints() {
        int points = 0;
        for (CardInterface card : deckOfCards) {
            points += card.cardType().getPoints();
        }
        return points;
    }
}
