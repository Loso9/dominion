package sk.uniba.fmph.dcs;

import java.util.*;

public class Turn {

    private final TurnStatus ts;
    private final Deck deck;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Play play;
    private boolean endTurnCalled = false;
    private final ArrayList<BuyDeck> supply;

    public Turn(TurnStatus ts, List<BuyDeck> supply) {
        this.ts = ts;
        discardPile = new DiscardPile(new ArrayList<>());
        deck = new Deck(discardPile);
        hand = new Hand(deck);
        play = new Play();
        this.supply = new ArrayList<>(supply);
        resetTurnStatus();
    }

    public boolean playCard(int index) {
        if (!hand.isCardInHand(index)) return false;
        if (hand.isActionCard(index)) {
            if (ts.getActions() > 0) {
                ts.addActions(ts.getActions() - 1);
            } else return false;
        }
        Optional<CardInterface> cardToPlay = hand.play(index);
        if (cardToPlay.isPresent()) {
            int plusCards = cardToPlay.get().evaluate(ts);
            play.putTo(cardToPlay.get());
            hand.addCardsToHand(plusCards);
            return true;
        }
        else return false;
    }

    public boolean playCard(CardInterface card) {
        if (!hand.isCardInHand(card)) return false;
        if (hand.isActionCard(card)) {
            if (ts.getActions() > 0) {
                ts.addActions(ts.getActions() - 1);
            } else return false;
        }
        Optional<CardInterface> cardToPlay = hand.play(card);
        if (cardToPlay.isPresent()) {
            int plusCards = cardToPlay.get().evaluate(ts);
            play.putTo(cardToPlay.get());
            hand.addCardsToHand(plusCards);
            return true;
        }
        else return false;
    }

    //index of BuyDeck
    public boolean buyCard(int index) {
        BuyDeck buyDeck = supply.get(index);
        if (buyDeck == null) return false;
        if (ts.getBuys() > 0 && !buyDeck.isEmpty() && ts.getCoins() >= buyDeck.costOfCard()) {
            Optional<CardInterface> newCard = buyDeck.buy();
            if (newCard.isPresent()) {
                ts.addCoins(-newCard.get().cardType().getCost()); //subtract
                ts.addBuys(-1);
                discardPile.addCard(newCard.get());
                return true;
            }
        }
        return false;
    }

    public boolean buyCard(CardInterface card) {
        BuyDeck buyDeck = findBuyDeck(card);
        if (buyDeck == null) return false;
        if (ts.getBuys() > 0 && !buyDeck.isEmpty() && ts.getCoins() >= card.cardType().getCost()) {
            Optional<CardInterface> newCard = buyDeck.buy();
            if (newCard.isPresent()) {
                ts.addCoins(-newCard.get().cardType().getCost()); //subtract
                ts.addBuys(-1);
                discardPile.addCard(newCard.get());
                return true;
            }
        }
        return false;
    }

    public void endTurn() {
        if (!endTurnCalled) {
            discardPile.addCards(play.throwAll());
            discardPile.addCards(hand.throwCards());
            endTurnCalled = true;
            resetTurnStatus();
            hand.addCardsToHand(5);
        }
    }


    public BuyDeck findBuyDeck(CardInterface card) {
        GameCardType cardType = card.cardType();
        for (BuyDeck deck : supply) {
            if (deck.getGameCardType().equals(cardType)) {
                return deck;
            }
        }
        return null;
    }

    public void resetTurnStatus() {
        ts.setCoins(0);
        ts.setActions(1);
        ts.setBuys(1);
    }

    public void setPoints() {
        int points = deck.getPoints() + discardPile.getPoints();
        ts.setPoints(points);
    }
}
