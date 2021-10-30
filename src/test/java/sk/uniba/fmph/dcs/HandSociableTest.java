package sk.uniba.fmph.dcs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HandSociableTest {

    private Deck deck1;
    private Deck deck4;

    private DiscardPile discardPile1;
    private DiscardPile discardPile2;
    private DiscardPile discardPile4;

    private Hand hand1;
    private Hand hand2;
    private Hand hand3;
    private Hand hand4;

    private List<CardInterface> deckOfCards3;
    private List<CardInterface> deckOfCards4;

    private void setUp() {
        ArrayList<CardInterface> deckOfCards1;
        ArrayList<CardInterface> deckOfCards2;

        deckOfCards1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            deckOfCards1.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }
        for (int j = 0; j < 3; j++) {
            deckOfCards1.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }

        ArrayList<CardInterface> listForDiscardPile1 = new ArrayList<>();
        listForDiscardPile1.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        listForDiscardPile1.add(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY));
        listForDiscardPile1.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        listForDiscardPile1.add(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));

        discardPile1 = new DiscardPile(listForDiscardPile1);
        deck1 = new Deck(discardPile1, deckOfCards1);

        ArrayList<CardInterface> listForDiscardPile2 = new ArrayList<>();
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDiscardPile2.add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));

        discardPile2 = new DiscardPile(listForDiscardPile2);
        deckOfCards2 = new ArrayList<>();
        Deck deck2 = new Deck(discardPile2, deckOfCards2);

        deckOfCards3 = new ArrayList<>();
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
        deckOfCards3.add(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE));

        ArrayList<CardInterface> listForDiscardPile3 = new ArrayList<>();
        DiscardPile discardPile3 = new DiscardPile(listForDiscardPile3);
        Deck deck3 = new Deck(discardPile3, deckOfCards3);

        ArrayList<CardInterface> listForDiscardPile4 = new ArrayList<>();
        deckOfCards4 = new ArrayList<>();
        discardPile4 = new DiscardPile(listForDiscardPile4);
        deck4 = new Deck(discardPile4, deckOfCards4);

        hand1 = new Hand(deck1);
        hand2 = new Hand(deck2);
        hand3 = new Hand(deck3);
        hand4 = new Hand(deck4);

    }

    @Test
    public void sociableTestHand() {
        setUp();
        hand1.throwCards();
        hand2.throwCards();
        hand3.throwCards();
        hand4.throwCards();

        assertEquals(0, hand1.size());
        assertEquals(0, hand2.size());
        assertEquals(0, hand3.size());
        assertEquals(0, hand3.size());

        hand1.addCardsToHand(5);
        hand2.addCardsToHand(5);
        hand3.addCardsToHand(5);
        hand4.addCardsToHand(5);

        ArrayList<CardInterface> thrownCards1 = new ArrayList<>(hand1.throwCards());
        ArrayList<CardInterface> thrownCards2 = new ArrayList<>(hand2.throwCards());
        ArrayList<CardInterface> thrownCards3 = new ArrayList<>(hand3.throwCards());
        ArrayList<CardInterface> thrownCards4 = new ArrayList<>(hand4.throwCards());

        assertEquals(4, thrownCards1.size());
        assertEquals(5, thrownCards2.size());
        assertEquals(3, thrownCards3.size());
        assertEquals(0, thrownCards4.size());

        assertEquals(0, discardPile1.getSize());
        assertEquals(0, discardPile2.getSize());
        assertEquals(8, deckOfCards3.size());

        assertEquals(0, discardPile4.getSize());
        assertEquals(0, hand4.size());
        assertEquals(0, deckOfCards4.size());
    }

    @Test
    public void addNothingTest() {
        setUp();

        assertEquals(0, hand4.size());
        assertEquals(0, discardPile4.getSize());
        assertEquals(0, deck4.getDeckOfCards().size());
        hand4.addCardsToHand(10);
        assertEquals(0, hand4.size());


    }

    @Test
    public void addTooMuchTest() {

        setUp();
        hand1.addCardsToHand(20);
        assertEquals(9, hand1.size());
        assertEquals(0, deck1.getDeckOfCards().size());
        assertEquals(0, discardPile1.getSize());
    }

}
