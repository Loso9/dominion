package sk.uniba.fmph.dcs;

import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class PlayTest {

    /*
     * Tests needed:
     * 1. is Play empty or not empty after adding/throwing cards
     * 2. Are the correct cards added and thrown away
     */

    private Play play;

    private void setUp() {

        play = new Play();

    }

    private void assertEmpty(Play play) {
        assertTrue(play.isEmpty());
    }

    private void assertNotEmpty(Play play) {
        assertFalse(play.isEmpty());
    }

    //let n different types of cards in play, does throwing all returns List of all of them (their count)
    // ! gameCardTypes.size() == counts.size();
    private void assertReturnCards(Play play, Map<GameCardType, Integer> cards) {
        Map<GameCardType, Integer> returnCards = new HashMap<>();
        ArrayList<CardInterface> thrownCards = new ArrayList<>(play.throwAll());
        for (CardInterface card : thrownCards) {
            GameCardType cardType = card.cardType();
            if (!returnCards.containsKey(cardType)) {
                returnCards.put(cardType, 1);
            } else {
                returnCards.put(cardType, returnCards.get(cardType) + 1);
            }
        }

        assertEquals(returnCards, cards);
    }

    @Test
    public void playTest() {
        setUp();
        assertEmpty(play);
        FakeCard card = new FakeCard(GameCardType.GAME_CARD_TYPE_COPPER);
        play.putTo(card);
        assertNotEmpty(play);

        play.throwAll();
        assertEmpty(play);

        Map<GameCardType, Integer> thrownCards = new HashMap<>();
        thrownCards.put(GameCardType.GAME_CARD_TYPE_COPPER, 3);
        thrownCards.put(GameCardType.GAME_CARD_TYPE_ESTATE, 4);
        thrownCards.put(GameCardType.GAME_CARD_TYPE_FESTIVAL, 7);


        for (int i = 0; i < 3; i++) {
            play.putTo(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }
        for (int i = 0; i < 4; i++) {
            play.putTo(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }
        for (int i = 0; i < 7; i++) {
            play.putTo(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        }

        assertReturnCards(play, thrownCards);
        assertEmpty(play);
    }

}
