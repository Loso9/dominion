package sk.uniba.fmph.dcs;


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
        return gameCardType.getPlusCards();
    }

    @Override
    public GameCardType cardType() {
        return gameCardType;
    }
}
