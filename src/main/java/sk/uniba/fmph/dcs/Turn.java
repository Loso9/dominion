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
        if (!hand.isCardInHand(index)) return false;
        if (hand.isActionCard(index) && ts.getActions() > 0) {
            ts.addActions(ts.getActions() - 1);
        }
        else return false;
        Optional<CardInterface> cardToPlay = hand.play(index);
        cardToPlay.ifPresent(cardInterface -> play.putTo(cardInterface));
        return true;
    }

    public boolean buyCard(CardInterface card) {
        BuyDeck buyDeck = findBuyDeck(card);
        if (ts.getBuys() > 0 && !buyDeck.isEmpty() && ts.getCoins() >= card.cardType().getCost()) {
            ts.addCoins(-card.cardType().getCost());
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
            resetTurnStatus();
            hand.addCardsToHand(deck.draw(5));
            return true;
        }
        else return false;
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
        int points = 0;
        ArrayList<CardInterface> cardsFromDeck = new ArrayList<>(deck.getDeckOfCards());
        cardsFromDeck.addAll(hand.getCards());
        cardsFromDeck.addAll(discardPile.getCards());
        for (CardInterface cardInterface : cardsFromDeck) {
            if (cardInterface.equals(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE))) {
                points++;
            }
        }
        ts.setPoints(points);

    }
}
