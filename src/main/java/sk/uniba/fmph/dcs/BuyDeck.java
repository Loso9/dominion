package sk.uniba.fmph.dcs;

import java.util.*;

public class BuyDeck {

    private final ArrayList<CardInterface> supply;
    private int countCard;
    private final GameCardType gameCardType;
    private final TurnStatus ts;

    public BuyDeck(TurnStatus ts, GameCardType gameCardType, int countCard) {
        this.ts = ts;
        this.gameCardType = gameCardType;
        this.supply = new ArrayList<>();
        this.countCard = countCard;
        for (int i = 0; i < countCard; i++) {
            supply.add(new GameCard(gameCardType));
        }
    }

    public Optional<CardInterface> buy() {
        CardInterface card = null;
        if (ts.getBuys() > 0) {
            card = supply.remove(0);
            countCard--;
        }
        return Optional.ofNullable(card);
    }

    public int getCountCard() {
        return countCard;
    }

    public boolean isEmpty() {
        return getCountCard() == 0;
    }

    public GameCardType getGameCardType() {
        return gameCardType;
    }
}
