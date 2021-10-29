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
        return nEmptyDecks() || emptyProvinceCardDeck();
    }

    public boolean nEmptyDecks() {
        int count = 0;
        for (BuyDeck deck : supply) {
            if (deck.isEmpty()) {
                count++;
            }
        }
        return count >= numOfEmptyDecks;
    }

    public boolean emptyProvinceCardDeck() {
        BuyDeck provinceBuyDeck = findBuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE);
        return provinceBuyDeck.isEmpty();
    }

    //cant return null if supply is well-constructed
    public BuyDeck findBuyDeck(GameCardType cardType) {
        BuyDeck buyDeckToFind = null;
        for (BuyDeck buyDeck : supply) {
            if (buyDeck.getGameCardType().equals(cardType)) {
                buyDeckToFind = buyDeck;
            }
        }
        return buyDeckToFind;
    }
}
