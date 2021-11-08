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
        supply2.add(new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1));

        turn1 = new Turn(ts1, supply1, true);
        turn2 = new Turn(ts2, supply2, true);
    }

    @Test
    public void turnTest() {
        setUp();
        turn1.endTurn();

        assertEquals(1, ts1.getActions());
        assertEquals(1, ts1.getBuys());
        assertEquals(0, ts1.getCoins());

        //cant buy from supply, when the buyDeck for that card isnt present
        assertFalse(turn1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY)));
        //cant buy from empty deck
        assertFalse(turn2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE)));

        assertEquals(0, ts1.getPoints());
        assertEquals(0, ts2.getPoints());

        turn1.endTurn();

        assertEquals(1, ts1.getActions());
        assertEquals(1, ts1.getBuys());
        assertEquals(0, ts1.getCoins());

    }
}
