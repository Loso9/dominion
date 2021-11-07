package sk.uniba.fmph.dcs;

import java.util.*;

public class NonShufflingDiscardPile implements DiscardPileInterface {

    private final LinkedList<CardInterface> cards;

    public NonShufflingDiscardPile(List<CardInterface> cards) {
        this.cards = new LinkedList<>(cards);
    }

    @Override
    public Optional<CardInterface> getTopCard() {
        if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size()-1));
    }

    @Override
    public void addCards(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public void addCard(CardInterface card) {
        this.cards.add(card);
    }

    @Override
    public List<CardInterface> getShuffledCards() {
        return getCards();
    }

    @Override
    public List<CardInterface> getCards() {
        return cards;
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public int getPoints() {
        int points = 0;
        for (CardInterface card : cards) {
            points += card.cardType().getPoints();
        }
        return points;
    }
}
