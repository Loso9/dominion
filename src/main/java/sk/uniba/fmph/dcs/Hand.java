package sk.uniba.fmph.dcs;

import java.util.*;

public class Hand {

    private final LinkedList<CardInterface> cardsInHand;
    private final DeckInterface deckOfCards;

    public Hand(DeckInterface deckOfCards) {
        this.deckOfCards = deckOfCards;
        cardsInHand = new LinkedList<>();
        addCardsToHand(5);

    }

    public boolean isActionCard(int index) {
        if (!isCardInHand(index)) return false;
        return cardsInHand.get(index).cardType().isAction();
    }

    public boolean isActionCard(CardInterface card) {
        if (!isCardInHand(card)) return false;
        return card.cardType().isAction();
    }

    public Optional<CardInterface> play(int index) {
        if (!isCardInHand(index)) return Optional.empty();
        CardInterface card = cardsInHand.get(index);
        cardsInHand.remove(card);
        return Optional.of(card);
    }

    public Optional<CardInterface> play(CardInterface card) {
        if (!isCardInHand(card)) return Optional.empty();
        int indexOfCard = cardsInHand.indexOf(card);
        CardInterface newCard = cardsInHand.get(indexOfCard);
        cardsInHand.remove(newCard);
        return Optional.of(newCard);
    }

    public int size() {
        return cardsInHand.size();
    }

    public boolean isCardInHand(int index) {
        return index < size();
    }

    public boolean isCardInHand(CardInterface card) {
        return cardsInHand.contains(card);
    }

    public List<CardInterface> throwCards() {
        ArrayList<CardInterface> returnCards = new ArrayList<>(cardsInHand);
        cardsInHand.clear();
        return returnCards;
    }

    public void addCardsToHand(int countCard) {
        cardsInHand.addAll(deckOfCards.draw(countCard));
    }

    public List<CardInterface> getCards() {
        return cardsInHand;
    }
}
