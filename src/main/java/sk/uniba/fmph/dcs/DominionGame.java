package sk.uniba.fmph.dcs;

import java.util.*;

public class DominionGame implements SimpleDominionInterface {

    public static List<BuyDeck> supply;
    private final Game newGame;

    public DominionGame() {
        supply = createSupply();
        EndGameStrategy myEndGameStrategy = new AtleastNEmptyDecks(supply, 3);
        this.newGame = new Game(myEndGameStrategy, supply, true);
    }

    @Override
    public Optional<GameState> playCard(int handIdx) {
        boolean playCard = newGame.playCard(handIdx);
        if (!playCard) {
            System.out.println("You are either trying to play card in BuyPhase or Game has already ended.");
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameState> playCard(CardInterface card) {
        boolean playCard = newGame.playCard(card);
        if (!playCard) {
            System.out.println("You are either trying to play card in BuyPhase or Game has already ended.");
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameState> buyCard(int index) {
        boolean buyCard = newGame.buyCard(index);
        if (!buyCard) {
            System.out.println("You are either trying to buy in BuyPhase or Game has already ended.");
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameState> buyCard(CardInterface card) {
        boolean buyCard = newGame.buyCard(card);
        if (!buyCard) {
            System.out.println("You are either trying to buy in BuyPhase or Game has already ended.");
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameState> endTurn() {
        boolean endTurn = newGame.endTurn();
        if (!endTurn) {
            System.out.println("Game has already ended.");
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<GameState> endPlayCardPhase() {
        boolean endPlayCardPhase = newGame.endPlayCardPhase();
        if (!endPlayCardPhase) {
            System.out.println("Game has already ended or you are calling the method in BuyPhase");
            return Optional.empty();
        }
        return Optional.empty();
    }

    public List<BuyDeck> createSupply() {
        supply = new ArrayList<>();
        BuyDeck estates = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 8);
        supply.add(estates);
        BuyDeck smithies = new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, 10);
        supply.add(smithies);
        BuyDeck coppers = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 10);
        supply.add(coppers);
        BuyDeck markets = new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, 10);
        supply.add(markets);
        BuyDeck villages = new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, 10);
        supply.add(villages);
        BuyDeck festivals = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 10);
        supply.add(festivals);
        BuyDeck laboratories = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, 10);
        supply.add(laboratories);
        BuyDeck provinces = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 8);
        supply.add(provinces);
        return supply;
    }
}
