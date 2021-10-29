package sk.uniba.fmph.dcs;

import java.util.*;

public class AtleastNEmptyDecks implements EndGameStrategy {

    ArrayList<BuyDeck> supply;
    private final int numOfEmptyDecks;

    public AtleastNEmptyDecks(List<BuyDeck> supply, int numOfEmptyDecks) {
        this.numOfEmptyDecks = numOfEmptyDecks;
        this.supply = new ArrayList<>(supply);
    }

    @Override
    public boolean isGameOver() {
        int count = 0;
        for (BuyDeck deck : supply) {
            if (deck.isEmpty()) {
                count++;
            }
        }
        return count >= numOfEmptyDecks;
    }
}
