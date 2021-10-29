package sk.uniba.fmph.dcs;

import java.util.*;

public class Game {

    private EndGameStrategy endGameStrategy;
    private boolean isActionPhase = false;
    private final Turn turn;
    private ArrayList<BuyDeck> supply; //balicek balickov kariet
    private boolean end = false;
    private TurnStatus ts;
    private boolean winnerFound = false;

    public Game(EndGameStrategy endGameStrategy, List<BuyDeck> buyDecks) {
        this.endGameStrategy = endGameStrategy;
        this.supply = new ArrayList<>(buyDecks);
        DiscardPile discardPile = new DiscardPile(new ArrayList<>());
        Deck deck = new Deck(discardPile);
        //inicialize turnstatus
        setActionPhase(false);
        this.ts = new TurnStatus();
        ts.addActions(1);
        ts.addBuys(1);
        ts.addCoins(0);
        Hand hand = new Hand(deck);
        Play play = new Play(deck, hand, ts);
        turn = new Turn(ts, hand, deck, discardPile, play, this.supply);
    }

    /*
    * V oboch buyCard, playCard metodach nema vyznam skusat, ak uz je gameover
     */
    public boolean playCard(Integer index) {
        if (isGameOver()) {
            return false;
        }
        if (!isActionPhase()) {
            return false;
        }
        return turn.playCard(index);
    }

    public void setActionPhase(boolean bool) {
        isActionPhase = bool;
    }

    public boolean isActionPhase() {
        return isActionPhase;
    }


    public boolean buyCard(CardInterface card) {
        if (isGameOver()) return false;
        if (isActionPhase()) return false;
        return turn.buyCard(card);
    }

    public boolean endPlayCardPhase() {
        if (isGameOver()) return false;
        if (!isActionPhase()) return false;
        setActionPhase(false);
        return true;
    }

    public boolean endTurn() {
        if (isGameOver()) return false;
        turn.endTurn();
        boolean isEnd = endGameStrategy.isGameOver();
        if (isEnd) {
            endTheGame();
            printResults();
            return winnerFound;
        }
        setActionPhase(true);
        return true;
    }

    public boolean isGameOver() {
        return endGameStrategy.isGameOver();
    }

    public void endTheGame() {
        turn.setPoints();
        if (ts.getPoints() >= 15) {
            winnerFound = true;//winner found
        }
    }

    public boolean wasWinnerFound() {
        return winnerFound;
    }

    public void printResults() {
        if (wasWinnerFound()) {
            System.out.println("Hrac zvitazil s " + ts.getPoints());
        }
        else {
            System.out.println("Hra skoncila bez vitaza, hrac ziskal len " + ts.getPoints());
        }
    }
}
