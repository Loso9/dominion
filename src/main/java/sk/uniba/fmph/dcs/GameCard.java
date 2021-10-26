package sk.uniba.fmph.dcs;

public class GameCard implements CardInterface {
    private GameCardType gameCardType;

    public GameCard() {
        this.gameCardType = null;
    }

    public GameCard(GameCardType gameCardType) {
        this.gameCardType = gameCardType;
    }

    public void setGameCard(GameCardType gameCardType) {
        this.gameCardType = gameCardType;
    }

    @Override
    public void evaluate(TurnStatus ts) {

    }

    @Override
    public GameCardType cardType() {
        return gameCardType;
    }
}
