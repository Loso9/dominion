package sk.uniba.fmph.dcs;

import java.util.*;

public class DiscardPile implements DiscardPileInterface {

    private List<CardInterface> cards;

    public DiscardPile(List<CardInterface> cards) {
        this.cards = cards;
    }
        
    public Optional<CardInterface> getTopCard() {
    	if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size()-1));
    }
        
    public void addCards(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }
        
    public int getSize() {
        return cards.size();
    }

    public void addCard(CardInterface card) {
        cards.add(card);
    }

    public List<CardInterface> getCards() {
        return cards;
    }

    public int getPoints() {
        int points = 0;
        for (CardInterface card : cards) {
            points += card.cardType().getPoints();
        }
        return points;
    }

    public List<CardInterface> getShuffledCards() {
        Shuffler shuffler = new Shuffler(cards);
        List<CardInterface> cardsToSend = shuffler.shuffle();
        cards = new ArrayList<>();
        return cardsToSend;
    }

}
        
        
