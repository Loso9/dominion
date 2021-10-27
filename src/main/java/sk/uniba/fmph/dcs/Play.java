package sk.uniba.fmph.dcs;

import java.util.*;

public class Play {

    private final ArrayList<CardInterface> cardsInPlay;
    private DiscardPile discardPile;
    private final TurnStatus ts;
    private final Hand hand;
    private final Deck deck;

    public Play(Deck deck, Hand hand, DiscardPile discardPile, TurnStatus ts) {
        this.deck = deck;
        this.hand = hand;
        cardsInPlay = new ArrayList<>();
        this.discardPile = discardPile;
        this.ts = ts;
    }

    public void putTo(CardInterface card) {
        assert card != null;
        cardsInPlay.add(card);
        int cardsToDraw = card.evaluate(ts);
        if (cardsToDraw == 0) {
            return;
        }
        ArrayList<CardInterface> newCards = new ArrayList<>(deck.draw(cardsToDraw));
        hand.addCardsToHand(newCards);
    }

    public List<CardInterface> throwAll() {
        ArrayList<CardInterface> returnCards = new ArrayList<>(cardsInPlay);
        cardsInPlay.clear();
        return returnCards;
    }

}
