package sk.uniba.fmph.dcs;

import java.util.*;

public class Game {

    private EndGameStrategy endGameStrategy;
    private boolean isActionPhase;
    private boolean isBuyPhase;
    private final Turn turn;
    private ArrayList<BuyDeck> supply; //balicek balickov kariet

    /*
    pripadne List<BuyDeck> priamo v konstruktore Game
    ArrayList<BuyDeck> supply = new ArrayList<>();
    BuyDeck estates = new BuyDeck(GAME_CARD_TYPE_ESTATE, countCard);
    supply.add(estates);
    BuyDeck smithies = new BuyDeck(GAME_CARD_TYPE_SMITHY, countCard);
    supply.add(smithies);
    BuyDeck coppers = new BuyDeck(GAME_CARD_TYPE_COPPER, countCard);
    supply.add(coppers);
    BuyDeck markets = new BuyDeck(GAME_CARD_TYPE_MARKET, countCard);
    supply.add(markets);
    BuyDeck villages = new BuyDeck(GAME_CARD_TYPE_MARKET, countCard);
    supply.add(villages);
    BuyDeck festivals = new BuyDeck(GAME_CARD_TYPE_FESTIVAL, countCard);
    supply.add(festivals);
    BuyDeck laboratories = new BuyDeck(GAME_CARD_TYPE_LABORATORY, countCard);
    supply.add()

     */

    public Game(EndGameStrategy endGameStrategy, List<BuyDeck> buyDecks) {
        this.endGameStrategy = endGameStrategy;
        this.supply = new ArrayList<>(buyDecks);
        DiscardPile discardPile = new DiscardPile(new ArrayList<>());
        Deck deck = new Deck(discardPile);
        //inicialize turnstatus
        setBuyPhase(true);
        TurnStatus ts = new TurnStatus();
        ts.addActions(1);
        ts.addBuys(1);
        ts.addCoins(0);
        Hand hand = new Hand(deck);
        Play play = new Play(hand, discardPile, ts);
        turn = new Turn(ts, hand, deck, discardPile, play, this.supply);
    }

    public boolean playCard(Integer index) {
        if (!isActionPhase()) {
            return false;
        }
        return turn.playCard(index);
    }

    public void setActionPhase(boolean bool) {
        isActionPhase = bool;
    }

    public void setBuyPhase(boolean bool) {
        isBuyPhase = bool;
    }

    public boolean isActionPhase() {
        return isActionPhase;
    }

    public boolean isBuyPhase() {
        return isBuyPhase;
    }

    public boolean endPlayCard() {
        return false;
    }

    public boolean buyCard(CardInterface card) {
        if (!isBuyPhase()) {
            return false;
        }
        return turn.buyCard(card);
    }

    public boolean endTurn() {
        if (!isActionPhase() && !isBuyPhase()) {
            return turn.endTurn();
        }
        return false;
    }
}
