package sk.uniba.fmph.dcs;

import java.util.*;

public class Play {

    private final ArrayList<CardInterface> cardsInPlay;
    private DiscardPile discardPile;
    private final TurnStatus ts;
    private final Hand hand;

    public Play(Hand hand, DiscardPile discardPile, TurnStatus ts) {
        this.hand = hand;
        cardsInPlay = new ArrayList<>();
        this.discardPile = discardPile;
        this.ts = ts;
    }

    public void putTo(CardInterface card) {
        assert card != null;
        cardsInPlay.add(card);
        Map.Entry<Integer, CardInterface> map_entry = card.evaluate(ts);
        if (map_entry == null) {
            return;
        }
        ArrayList<CardInterface> newCards = new ArrayList<>();
        for (int i = 0; i < map_entry.getKey(); i++) {
            newCards.add(map_entry.getValue());
        }
        hand.addCardsToHand(newCards);
    }

    public List<CardInterface> throwAll() {
        ArrayList<CardInterface> returnCards = new ArrayList<>(cardsInPlay);
        cardsInPlay.clear();
        return returnCards;
    }

}
