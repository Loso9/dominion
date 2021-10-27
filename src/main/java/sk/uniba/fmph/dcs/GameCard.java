package sk.uniba.fmph.dcs;

import java.util.*;

public class GameCard implements CardInterface {

    private final GameCardType gameCardType;

    public GameCard(GameCardType gameCardType) {
        this.gameCardType = gameCardType;
    }

    @Override
    public int evaluate(TurnStatus ts) {
        ts.addActions(gameCardType.getPlusActions());
        ts.addCoins(gameCardType.getPlusCoins());
        ts.addBuys(gameCardType.getPlusBuys());
        //int plusCards = gameCardType.getPlusCards();
        return gameCardType.getPlusCards();
        //GameCard card = new GameCard(gameCardType);
        //return new AbstractMap.SimpleEntry<>(plusCards);
    }

    @Override
    public GameCardType cardType() {
        return gameCardType;
    }
}
