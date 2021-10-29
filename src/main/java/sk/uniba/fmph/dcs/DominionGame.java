package sk.uniba.fmph.dcs;

import java.util.Optional;
import java.util.*;

public class DominionGame implements SimpleDominionInterface {

    List<BuyDeck> supply;
    private final Game newGame;

    public DominionGame(int countCard) {
        supply = new ArrayList<>();
        BuyDeck estates = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, countCard);
        supply.add(estates);
        BuyDeck smithies = new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, countCard);
        supply.add(smithies);
        BuyDeck coppers = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, countCard);
        supply.add(coppers);
        BuyDeck markets = new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, countCard);
        supply.add(markets);
        BuyDeck villages = new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, countCard);
        supply.add(villages);
        BuyDeck festivals = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, countCard);
        supply.add(festivals);
        BuyDeck laboratories = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, countCard);
        supply.add(laboratories);

        EndGameStrategy myEndGameStrategy = new AtleastNEmptyDecks(supply, 3);
        this.newGame = new Game(myEndGameStrategy, supply);
    }

    @Override
    public Optional<GameState> playCard(int handIdx) {
        newGame.playCard(handIdx);
        return Optional.empty();
    }


    @Override
    public Optional<GameState> buyCard(CardInterface card) {
        newGame.buyCard(card);
        return Optional.empty();
    }

    @Override
    public Optional<GameState> endTurn() {
        newGame.endTurn();
        return Optional.empty();
    }
}
