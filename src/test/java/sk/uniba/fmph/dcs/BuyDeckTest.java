package sk.uniba.fmph.dcs;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BuyDeckTest {

    /*
     * Tests needed:
     * 1. Is buyDeck empty or not
     * 2. Does buyDeck contain n cards
     * 3. Does buyDeck contain specific type of card
     * 4. Does buying card from BuyDeck return expected type of card (null/card) and does it reduce the size
     */

    BuyDeck testBuyDeck1;
    BuyDeck testBuyDeck2;
    BuyDeck testBuyDeck3;
    BuyDeck testBuyDeck4;

    private void setUp() {

        testBuyDeck1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_VILLAGE, 1);
        testBuyDeck2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 4);
        testBuyDeck3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 5);
        testBuyDeck4 = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 0);

    }

    private void assertEmpty(BuyDeck buyDeck) {
        assertTrue(buyDeck.isEmpty());
    }

    private void assertNotEmpty(BuyDeck buyDeck) {
        assertFalse(buyDeck.isEmpty());
    }

    private void assertSize(BuyDeck buyDeck, int size) {
        assertEquals(size, buyDeck.size());
    }

    private void assertCardType(BuyDeck buyDeck, GameCardType cardType) {
        assertEquals(cardType, buyDeck.getGameCardType());
    }


    //testing first 4 questions
    @Test
    public void buyDeckTest() {
        setUp();

        //tests for testBuyDeck1
        assertCardType(testBuyDeck1, GameCardType.GAME_CARD_TYPE_VILLAGE);
        assertNotEmpty(testBuyDeck1);
        assertSize(testBuyDeck1, 1);
        Optional<CardInterface> buyCard1 = testBuyDeck1.buy();
        assertTrue(buyCard1.isPresent());
        assertEmpty(testBuyDeck1);
        assertSize(testBuyDeck1, 0);

        Optional<CardInterface> buyEmptyCard = testBuyDeck1.buy();
        assertFalse(buyEmptyCard.isPresent());
        assertEmpty(testBuyDeck1);
        assertSize(testBuyDeck1, 0);


        //tests for testBuyDeck2
        assertCardType(testBuyDeck2, GameCardType.GAME_CARD_TYPE_FESTIVAL);
        assertNotEmpty(testBuyDeck2);
        assertSize(testBuyDeck2, 4);
        Optional<CardInterface> buyCard2 = testBuyDeck2.buy();
        assertTrue(buyCard2.isPresent());
        assertNotEmpty(testBuyDeck2);
        assertSize(testBuyDeck2, 3);

        //tests for testBuyDeck3
        assertCardType(testBuyDeck3, GameCardType.GAME_CARD_TYPE_PROVINCE);
        assertNotEmpty(testBuyDeck3);
        assertSize(testBuyDeck3, 5);
        Optional<CardInterface> buyCard3 = testBuyDeck3.buy();
        assertTrue(buyCard3.isPresent());
        assertNotEmpty(testBuyDeck3);
        assertSize(testBuyDeck3, 4);

        //tests for testBuyDeck4
        assertCardType(testBuyDeck4, GameCardType.GAME_CARD_TYPE_ESTATE);
        assertEmpty(testBuyDeck4);
        assertSize(testBuyDeck4, 0);
        Optional<CardInterface> buyCard4 = testBuyDeck4.buy();
        assertFalse(buyCard4.isPresent());
        assertEmpty(testBuyDeck4);
        assertSize(testBuyDeck4, 0);

    }

}
