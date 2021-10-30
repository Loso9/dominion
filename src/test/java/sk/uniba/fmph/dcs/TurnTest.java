package sk.uniba.fmph.dcs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TurnTest {

    private Turn turn1;
    private Turn turn2;
    private TurnStatus ts1;
    private TurnStatus ts2;

    private void setUp() {

        ts1 = new TurnStatus();
        ts2 = new TurnStatus();

        ArrayList<BuyDeck> supply1 = new ArrayList<>();
        ArrayList<BuyDeck> supply2 = new ArrayList<>();

        BuyDeck buyDeck1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 10);
        BuyDeck buyDeck2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 10);
        BuyDeck buyDeck3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 10);
        BuyDeck buyDeck4 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 8);
        BuyDeck buyDeck5 = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, 10);
        BuyDeck buyDeck6 = new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, 10);
        BuyDeck buyDeck7 = new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, 10);
        BuyDeck buyDeck8 = new BuyDeck(GameCardType.GAME_CARD_TYPE_VILLAGE, 0);
        addBuyDecks(supply1, buyDeck1, buyDeck2, buyDeck3, buyDeck4, buyDeck5);
        addBuyDecks(supply2, buyDeck1, buyDeck3, buyDeck2, buyDeck6, buyDeck8, buyDeck7);

        turn1 = new Turn(ts1, supply1);
        turn2 = new Turn(ts2, supply2);
    }

    private void addBuyDecks(ArrayList<BuyDeck> buyDeck, BuyDeck... buyDecks) {
        buyDeck.addAll(Arrays.asList(buyDecks));
    }

    @Test
    public void turnTest() {
        setUp();
        turn1.endTurn();
        turn2.endTurn();

        assertEquals(1, ts1.getActions());
        assertEquals(1, ts2.getActions());

        assertEquals(1, ts1.getBuys());
        assertEquals(1, ts2.getBuys());

        assertEquals(0, ts1.getCoins());
        assertEquals(0, ts2.getCoins());

        //cant buy from supply, when the buyDeck for that card isnt present
        assertFalse(turn1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY)));
        //cant buy from empty deck
        assertFalse(turn2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE)));

        assertTrue(turn1.playCard(0));

        assertTrue(turn1.playCard(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertTrue(turn2.playCard(new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));

        assertFalse(turn1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET)));
        assertTrue(turn2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE)));

        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));
        assertFalse(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));

        assertEquals(0, ts1.getPoints());
        assertEquals(4, ts2.getPoints());

        turn1.endTurn();
        turn1.endTurn(); //nothing should happen internally
        turn2.endTurn();

        assertEquals(1, ts1.getActions());
        assertEquals(1, ts2.getActions());

        assertEquals(1, ts1.getBuys());
        assertEquals(1, ts2.getBuys());

        assertEquals(0, ts1.getCoins());
        assertEquals(0, ts2.getCoins());

        ts2.addCoins(30);
        ts2.addActions(3);
        ts2.addBuys(3);
        assertTrue(turn2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE)));
        assertTrue(turn2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE)));
        assertTrue(turn2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE)));

        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE)));
        assertTrue(turn2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_PROVINCE)));

        assertEquals(0, ts1.getPoints());
        assertEquals(22, ts2.getPoints());

        turn1.endTurn();
        turn2.endTurn();

        assertEquals(1, ts1.getActions());
        assertEquals(1, ts2.getActions());

        assertEquals(1, ts1.getBuys());
        assertEquals(1, ts2.getBuys());

        assertEquals(0, ts1.getCoins());
        assertEquals(0, ts2.getCoins());

    }
}
