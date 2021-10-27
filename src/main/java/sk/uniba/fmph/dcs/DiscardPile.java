package sk.uniba.fmph.dcs;

import java.util.*;

public class DiscardPile {
    List<CardInterface> cards;

    public DiscardPile() {
        cards = new ArrayList<>();
    }

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
        
    public List<CardInterface> shuffle() {
        Collections.shuffle(cards);
        List<CardInterface> cardsToSend = cards;
        cards = new ArrayList<>();
        return cardsToSend;
    }

    public List<CardInterface> getCards() {
        return cards;
    }

}
        
        
