package sk.uniba.fmph.dcs;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class AtleastNEmptyDecksTest {

    private ArrayList<BuyDeck> supply;
    private AtleastNEmptyDecks atleast0EmptyDecksTest;
    private AtleastNEmptyDecks atleast1EmptyDeckTest;
    private AtleastNEmptyDecks atleast3EmptyDecksTest;

    private void setUp() {

        BuyDeck buyDeck1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 10);
        BuyDeck buyDeck2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 10);
        BuyDeck buyDeck3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 10);
        BuyDeck buyDeck4 = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, 10);
        BuyDeck buyDeck5 = new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, 10);
        addLists(buyDeck1, buyDeck2, buyDeck3, buyDeck4, buyDeck5);

        atleast0EmptyDecksTest = new AtleastNEmptyDecks(supply, 0);
        atleast1EmptyDeckTest = new AtleastNEmptyDecks(supply, 1);
        atleast3EmptyDecksTest = new AtleastNEmptyDecks(supply, 3);

    }

    private void addLists(BuyDeck... lists) {
        supply = new ArrayList<>(Arrays.asList(lists));
    }

    @Test
    public void atleastNEmptyDecksTest() {
        setUp();
        assertTrue(atleast0EmptyDecksTest.isGameOver());
        assertFalse(atleast1EmptyDeckTest.isGameOver());
        assertFalse(atleast3EmptyDecksTest.isGameOver());

        //adding one empty deck should end game for game with atleast1EmptyDeckTest strategy
        BuyDeck emptyBuyDeck1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, 0);
        supply.add(emptyBuyDeck1);
        assertTrue(atleast0EmptyDecksTest.isGameOver());
        assertTrue(atleast1EmptyDeckTest.isGameOver());
        assertFalse(atleast3EmptyDecksTest.isGameOver());

        //adding two empty decks shouldnt end game for game with atleast3EmptyDecksTest strategy
        BuyDeck emptyBuyDeck2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_VILLAGE, 0);
        supply.add(emptyBuyDeck2);
        assertTrue(atleast0EmptyDecksTest.isGameOver());
        assertTrue(atleast1EmptyDeckTest.isGameOver());
        assertFalse(atleast3EmptyDecksTest.isGameOver());

        BuyDeck emptyBuyDeck3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, 0);
        supply.add(emptyBuyDeck3);
        assertTrue(atleast0EmptyDecksTest.isGameOver());
        assertTrue(atleast1EmptyDeckTest.isGameOver());
        assertTrue(atleast3EmptyDecksTest.isGameOver());

        supply.remove(emptyBuyDeck1);
        supply.remove(emptyBuyDeck2);
        supply.remove(emptyBuyDeck3);

        BuyDeck emptyProvinces = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 0);
        AtleastNEmptyDecks newAtleast5EmptyDeck = new AtleastNEmptyDecks(supply, 5);
        assertFalse(newAtleast5EmptyDeck.isGameOver());

        supply.add(emptyProvinces);
        assertTrue(newAtleast5EmptyDeck.isGameOver());

    }

}
