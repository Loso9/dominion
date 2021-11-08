package sk.uniba.fmph.dcs;

import org.junit.*;
import static org.junit.Assert.*;

public class EmptyProvinceDeckTest {

    private EmptyProvinceDeck provinceDeckStrategy1;
    private EmptyProvinceDeck provinceDeckStrategy2;

    void setUp() {
        BuyDeck testBuyDeck1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 0);
        BuyDeck testBuyDeck2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 1);
        provinceDeckStrategy1 = new EmptyProvinceDeck(testBuyDeck1);
        provinceDeckStrategy2 = new EmptyProvinceDeck(testBuyDeck2);
    }

    @Test
    public void emptyProvinceDeckTest() {
        setUp();
        assertTrue(provinceDeckStrategy1.isGameOver());
        assertFalse(provinceDeckStrategy2.isGameOver());
    }

}
