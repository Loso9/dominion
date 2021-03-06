package sk.uniba.fmph.dcs;

import java.util.*;

public class Game {

    private final ArrayList<EndGameStrategy> endGameStrategy;
    private boolean isActionPhase = false;
    private final Turn turn;
    private final TurnStatus ts;
    private boolean winnerFound;
    private boolean gameEnded;

    public Game(List<BuyDeck> buyDecks, EndGameStrategy... endGameStrategy) {
        this.endGameStrategy = new ArrayList<>(Arrays.asList(endGameStrategy));
        //balicek balickov kariet
        ArrayList<BuyDeck> supply = new ArrayList<>(buyDecks);
        //inicialize turnstatus
        setActionPhase(true);
        this.ts = new TurnStatus();
        turn = new Turn(ts, supply, true);
        winnerFound = false;
        gameEnded = false;
    }

    public boolean playCard(int index) {
        if (isGameOver()) {
            return false;
        }
        if (!isActionPhase()) {
            return false;
        }
        return turn.playCard(index);
    }

    public boolean playCard(CardInterface card) {
        if (isGameOver()) {
            return false;
        }
        if (!isActionPhase()) {
            return false;
        }
        return turn.playCard(card);
    }

    public void setActionPhase(boolean bool) {
        isActionPhase = bool;
    }

    public boolean isActionPhase() {
        return isActionPhase;
    }

    public boolean buyCard(int index) {
        if (isGameOver()) return false;
        if (isActionPhase()) return false;
        return turn.buyCard(index);
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
        boolean isEnd = isGameOver();
        if (isEnd) {
            endTheGame();
            printResults();
            return true;
        }
        setActionPhase(true);
        return true;
    }

    public boolean isGameOver() {
        if (gameEnded) return true;
        boolean endGameStrategyBoolean = false;
        for (EndGameStrategy strategy : endGameStrategy) {
            endGameStrategyBoolean = endGameStrategyBoolean || strategy.isGameOver();
        }
        return endGameStrategyBoolean;
    }

    public void endTheGame() {
        setEndGame();
        turn.setPoints();
        if (ts.getPoints() >= 15) {
            winnerFound = true;//winner found
        }
    }

    public boolean wasWinnerFound() {
        return winnerFound;
    }

    public boolean wasGameFinished() {
        return gameEnded;
    }

    public void printResults() {
        if (wasWinnerFound()) {
            System.out.println("Player won with " + ts.getPoints() + " points.");
        }
        else {
            System.out.println("Game ended with no winner, player only got " + ts.getPoints() + " points.");
        }
    }

    public int getPoints() {
        turn.setPoints();
        return ts.getPoints();
    }

    public void setEndGame() {
        gameEnded = true;
    }

}
