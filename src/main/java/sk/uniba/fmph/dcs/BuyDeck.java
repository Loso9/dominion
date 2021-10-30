package sk.uniba.fmph.dcs;

import java.util.*;

public class BuyDeck {

    private final LinkedList<CardInterface> buyDeck;
    private final GameCardType gameCardType;

    public BuyDeck(GameCardType gameCardType, int cardCount) {
        this.gameCardType = gameCardType;
        this.buyDeck = new LinkedList<>();
        for (int i = 0; i < cardCount; i++) {
            buyDeck.add(new GameCard(gameCardType));
        }
    }

    public Optional<CardInterface> buy() {
        CardInterface card;
        card = buyDeck.removeFirst();
        return Optional.ofNullable(card);
    }

    public int size() {
        return buyDeck.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public GameCardType getGameCardType() {
        return gameCardType;
    }

    public int costOfCard() {
        return gameCardType.getCost();
    }
}
