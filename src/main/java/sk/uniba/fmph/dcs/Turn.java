package sk.uniba.fmph.dcs;

import java.util.*;

public class Turn {

    private TurnStatus ts;
    private Deck deck;
    private Hand hand;
    private DiscardPile discardPile;
    private Play play;
    private boolean endTurnCalled = false;
    private ArrayList<BuyDeck> supply;

    public Turn(TurnStatus ts) {
        this.ts = ts;
    }

    public Turn(TurnStatus ts, Hand hand, Deck deck, DiscardPile discardPile, Play play, List<BuyDeck> supply) {
        this.ts = ts;
        this.deck = deck;
        this.hand = hand;
        this.discardPile = discardPile;
        this.play = play;
        this.supply = new ArrayList<>(supply);
    }

    public boolean playCard(Integer index) {
        if (ts.getActions() > 0 && hand.isCardInHand(index) && hand.isActionCard(index)) {
            Optional<CardInterface> cardToPlay = hand.play(index);
            cardToPlay.ifPresent(cardInterface -> play.putTo(cardInterface));
            return true;
        }
        return false;
    }

    public boolean buyCard(CardInterface card) {
        BuyDeck buyDeck = findBuyDeck(card);
        if (ts.getBuys() > 0 && !buyDeck.isEmpty()) {
            Optional<CardInterface> newCard = buyDeck.buy();
            if (newCard.isPresent()) {
                hand.addCardToHand(newCard.get());
                return true;
            }
        }
        return false;
    }

    public boolean endTurn() {
        if (!endTurnCalled) {
            discardPile.addCards(play.throwAll());
            discardPile.addCards(hand.throwCards());
            endTurnCalled = true;
            return true;
        }
        else return false;
    }

    /*
    public boolean firstTurn() {
        return false;
    }
    */

    public BuyDeck findBuyDeck(CardInterface card) {
        GameCardType cardType = card.cardType();
        for (BuyDeck deck : supply) {
            if (deck.getGameCardType().equals(cardType)) {
                return deck;
            }
        }
        return null;
    }

    public boolean isGameOver() {
        return false;
    }
}
