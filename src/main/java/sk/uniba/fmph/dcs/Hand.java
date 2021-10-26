package sk.uniba.fmph.dcs;

import java.util.*;

public class Hand {

    private final LinkedList<CardInterface> cardsInHand;

    public Hand() {
        cardsInHand = new LinkedList<>();
    }

    public Hand(Deck deckOfCards) {
        cardsInHand = new LinkedList<>(deckOfCards.draw(5));

    }

    public boolean isActionCard(Integer index) {
        return cardsInHand.get(index).cardType().isAction();
    }

    public Optional<CardInterface> play(Integer index) {
        if (index > cardsInHand.size()) {
            return Optional.empty();
        }
        return Optional.of(cardsInHand.get(index));
    }

    public Integer size() {
        return cardsInHand.size();
    }

    public boolean isCardInHand(Integer index) {
        return index < size();
    }

    public List<CardInterface> throwCards() {
        ArrayList<CardInterface> returnCards = new ArrayList<>(cardsInHand);
        cardsInHand.clear();
        return returnCards;
    }

    public void addCardsToHand(List<CardInterface> cards) {
        cardsInHand.addAll(cards);
    }

    public void addCardToHand(CardInterface card) {
        cardsInHand.add(card);
    }
}
