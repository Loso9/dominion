package sk.uniba.fmph.dcs;

public interface CardInterface {
    int evaluate(TurnStatus ts);
    GameCardType cardType();
}


