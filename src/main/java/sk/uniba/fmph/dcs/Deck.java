package sk.uniba.fmph.dcs;

import java.util.*;

public class Deck {

    private LinkedList<CardInterface> deckOfCards;

    public Deck() {
        deckOfCards = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            deckOfCards.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }

        for (int j = 0; j < 7; j++) {
            deckOfCards.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }
        shuffle();
    }

    public Deck(List<CardInterface> deckOfCards) {
        this.deckOfCards = new LinkedList<>(deckOfCards);
    }

    public List<CardInterface> draw(Integer count) {
        int cardCount = count;
        ArrayList<CardInterface> returnList = new ArrayList<>();
        while (cardCount --> 0) {
            returnList.add(deckOfCards.removeFirst());
        }

        return Collections.unmodifiableList(returnList);
    }

    public void shuffle() {
        Collections.shuffle(deckOfCards);
    }

    public LinkedList<CardInterface> getDeckOfCards() {
        return deckOfCards;
    }
}
