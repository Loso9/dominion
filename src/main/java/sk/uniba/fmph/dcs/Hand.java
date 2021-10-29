package sk.uniba.fmph.dcs;

import java.util.*;

public class Hand {

    private final LinkedList<CardInterface> cardsInHand;
    private final Deck deckOfCards;

    public Hand(Deck deckOfCards) {
        this.deckOfCards = deckOfCards;
        cardsInHand = new LinkedList<>();
        addCardsToHand(5);

    }

    public boolean isActionCard(Integer index) {
        if (!isCardInHand(index)) return false;
        return cardsInHand.get(index).cardType().isAction();
    }

    public Optional<CardInterface> play(Integer index) {
        if (!isCardInHand(index)) return Optional.empty();
        CardInterface card = cardsInHand.get(index);
        cardsInHand.remove(card);
        return Optional.of(card);
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

    public void addCardsToHand(int countCard) {
        cardsInHand.addAll(deckOfCards.draw(countCard));
    }

    public void addCardToHand(CardInterface card) {
        cardsInHand.add(card);
    }

    public List<CardInterface> getCards() {
        return cardsInHand;
    }
}
