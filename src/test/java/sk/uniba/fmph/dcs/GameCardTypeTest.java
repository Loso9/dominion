package sk.uniba.fmph.dcs;

import org.junit.*;
import static org.junit.Assert.*;

public class GameCardTypeTest {

    GameCardType cardType1;
    GameCardType cardType2;
    GameCardType cardType3;

    private void setUp() {

        //new GameCardType(1, 0, 2, 0, 0, 5, true, "Laboratory", "+1 Action; +2 Cards");
        cardType1 = GameCardType.GAME_CARD_TYPE_LABORATORY;
        cardType2 = new GameCardType(2, 1, 1, 3, 0, 5, true, "card1", "testType1");
        cardType3 = new GameCardType(0, 0, 3, 0, 5, 2, false, "card2", "testType2");
    }

    @Test
    public void gameCardTypeTest() {
        setUp();
        assertEquals(1, cardType1.getPlusActions());
        assertEquals(2, cardType2.getPlusActions());
        assertEquals(0, cardType3.getPlusActions());

        assertEquals(0, cardType1.getPlusBuys());
        assertEquals(1, cardType2.getPlusBuys());
        assertEquals(0, cardType3.getPlusBuys());

        assertEquals(2, cardType1.getPlusCards());
        assertEquals(1, cardType2.getPlusCards());
        assertEquals(3, cardType3.getPlusCards());

        assertEquals(0, cardType1.getPlusCoins());
        assertEquals(3, cardType2.getPlusCoins());
        assertEquals(0, cardType3.getPlusCoins());

        assertEquals(0, cardType1.getPoints());
        assertEquals(0, cardType2.getPoints());
        assertEquals(5, cardType3.getPoints());

        assertEquals(5, cardType1.getCost());
        assertEquals(5, cardType2.getCost());
        assertEquals(2, cardType3.getCost());

        assertTrue(cardType1.isAction());
        assertTrue(cardType2.isAction());
        assertFalse(cardType3.isAction());

        assertEquals("Laboratory", cardType1.getName());
        assertEquals("card1", cardType2.getName());
        assertEquals("card2", cardType3.getName());

        assertEquals("+1 Action; +2 Cards", cardType1.getDescription());
        assertEquals("testType1", cardType2.getDescription());
        assertEquals("testType2", cardType3.getDescription());

    }
}
