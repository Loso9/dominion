package sk.uniba.fmph.dcs;

import java.util.*;

public class AtleastNEmptyDecks implements EndGameStrategy {

    ArrayList<BuyDeck> supply;

    public AtleastNEmptyDecks(List<BuyDeck> supply) {
        this.supply = new ArrayList<>(supply);
    }

    //ak su 3 buydecky prazdne
    @Override
    public boolean isGameOver() {
        int count = 0;
        for (BuyDeck deck : supply) {
            if (deck.isEmpty()) {
                count++;
            }
        }
        return count >= 3;
    }
}
