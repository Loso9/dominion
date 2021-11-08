package sk.uniba.fmph.dcs;

import java.util.*;

public class EmptyProvinceDeck implements EndGameStrategy {

    private final BuyDeck provinceDeck;

    public EmptyProvinceDeck(BuyDeck provinceDeck) {
        this.provinceDeck = provinceDeck;
    }

    @Override
    public boolean isGameOver() {
        return provinceDeck.isEmpty();
    }

}
