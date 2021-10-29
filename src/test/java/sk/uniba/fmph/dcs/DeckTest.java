package sk.uniba.fmph.dcs;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.assertEquals;


public class DeckTest {

    /*
    * Tests needed:
    * 1. Are points counted well
    * 2. Are cards drawn well
     */

    private Deck testDeck1;
    private Deck testDeck2;
    private Deck testDeck3;
    private Deck testDeck4;
    private Deck testDeck5;
    private Deck testDeck6;

    void setUp() {

        /*
         * variables for drawCountTesting
         */
        ArrayList<CardInterface> listForDiscardPile1 = new ArrayList<>();
        ArrayList<CardInterface> listForDiscardPile2 = new ArrayList<>();
        ArrayList<CardInterface> listForDeck1 = new ArrayList<>();
        ArrayList<CardInterface> listForDeck2 = new ArrayList<>();

        listForDiscardPile1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        listForDiscardPile1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        listForDiscardPile1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDiscardPile1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));

        listForDiscardPile2.add(new FakeCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        listForDiscardPile2.add(new FakeCard(GameCardType.GAME_CARD_TYPE_VILLAGE));

        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER));
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        listForDeck1.add(new FakeCard(GameCardType.GAME_CARD_TYPE_LABORATORY));

        listForDeck2.add(new FakeCard(GameCardType.GAME_CARD_TYPE_SMITHY));
        listForDeck2.add(new FakeCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        listForDeck2.add(new FakeCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));

        DiscardPile testDiscardPile1 = new DiscardPile(listForDiscardPile1);
        DiscardPile testDiscardPile2 = new DiscardPile(listForDiscardPile2);


        testDeck1 = new Deck(testDiscardPile1, listForDeck1); //both not empty
        testDeck2 = new Deck(new DiscardPile(new ArrayList<>()), listForDeck2); //empty discard pile
        testDeck3 = new Deck(testDiscardPile2, new ArrayList<>()); //empty list of cards for deck
        testDeck4 = new Deck(new DiscardPile(new ArrayList<>()), new ArrayList<>()); //both empty

        /*
         * variables for getPointsTesting
         */

        ArrayList<CardInterface> listForDeck5 = new ArrayList<>();
        ArrayList<CardInterface> listForDeck6 = new ArrayList<>();

        listForDeck5.add(new FakeCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        listForDeck5.add(new FakeCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        listForDeck5.add(new FakeCard(GameCardType.GAME_CARD_TYPE_MARKET));
        listForDeck5.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        listForDeck5.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        listForDeck5.add(new FakeCard(GameCardType.GAME_CARD_TYPE_PROVINCE));

        listForDeck6.add(new FakeCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        listForDeck6.add(new FakeCard(GameCardType.GAME_CARD_TYPE_PROVINCE));
        listForDeck6.add(new FakeCard(GameCardType.GAME_CARD_TYPE_ESTATE));

        testDeck5 = new Deck(new DiscardPile(new ArrayList<>()), listForDeck5);
        testDeck6 = new Deck(new DiscardPile(new ArrayList<>()), listForDeck6);

    }

    @Test
    public void drawCountTest() {
        setUp();
        assertEquals(5, testDeck1.draw(5).size());
        assertEquals(3, testDeck2.draw(5).size());
        assertEquals(2, testDeck3.draw(5).size());
        assertEquals(0, testDeck4.draw(5).size());

    }

    @Test
    public void getPointsTest() {
        setUp();
        assertEquals(1, testDeck1.getPoints());
        assertEquals(0, testDeck2.getPoints());
        assertEquals(8, testDeck5.getPoints());
        assertEquals(13, testDeck6.getPoints());
    }
}
