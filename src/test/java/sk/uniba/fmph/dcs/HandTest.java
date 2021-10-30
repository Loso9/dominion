package sk.uniba.fmph.dcs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HandTest {

    private static final GameCardType fakeCardTypeNoAction = new GameCardType(0, 0, 0, 0, 0, 0, false, "fakeNoAction", "0");
    private static final GameCardType fakeCardTypeAction = new GameCardType(0, 0, 0, 0, 0, 0, true, "fakeAction", "1");

    private static class FakeDeck implements DeckInterface {

        LinkedList<CardInterface> fakeDeckOfCards;

        /*
         * due to implementation of draw method, custom constructor is not needed, as when hand wants to
         * draw cards, fakedeck creates them in draw method
         */

        /*
         * draw method instead of reducing the size of deck, it creates the cards and adds them to deck
         * - first card is blank action card, and the other are blank noaction cards
         */
        @Override
        public List<CardInterface> draw(int count) {
            fakeDeckOfCards = new LinkedList<>();
            if (count == 0) return fakeDeckOfCards;
            fakeDeckOfCards.add(new FakeCard(fakeCardTypeAction));
            int n = count - 1;
            while (n --> 0) {
                fakeDeckOfCards.add(new FakeCard(fakeCardTypeNoAction));
            }
            return fakeDeckOfCards;
        }

        @Override
        public List<CardInterface> getDeckOfCards() {
            return fakeDeckOfCards;
        }

        @Override
        public int getPoints() {
            return 0;
        }
    }

    private FakeDeck fakeDeck;
    private Hand hand;
    private ArrayList<CardInterface> thrownCards;

    private void setUp() {
        fakeDeck = new FakeDeck();
        hand = new Hand(fakeDeck);
    }

    @Test
    public void assertInitHand() {
        setUp();

        int n = 50; //testCount
        for (int i = 0; i < 5; i++) {
            assertTrue(hand.isCardInHand(i));
        }
        for (int j = 5; j < 50; j++) {
            assertFalse(hand.isCardInHand(j));
        }
    }

    @Test
    public void assertDraws() {
        setUp();

        int n = 50; //testCount
        while (n --> 0) {
            //adds n-cards, then n-1 cards, ... -> n(n+1)/2 cards + init 5 cards
            hand.addCardsToHand(n);
        }
        thrownCards = new ArrayList<>(hand.throwCards());
        assertEquals(((long) (n) *(n+1))/2 + 5, thrownCards.size());

        for (int i = 0; i < ( n * (n + 1) / 2) + 5; i++) {
            if (i != 0) {
                assertFalse(thrownCards.get(i).cardType().isAction());
            }
            else {
                assertTrue(thrownCards.get(0).cardType().isAction());
            }
        }

    }

    @Test
    public void assertInitialThrow() {
        setUp();
        thrownCards = new ArrayList<>(hand.throwCards());
        assertEquals(fakeDeck.getDeckOfCards(), thrownCards);
        assertEquals(5, thrownCards.size());

        for (int i = 1; i < thrownCards.size() - 1; i++) {
            assertFalse(thrownCards.get(i).cardType().isAction());
        }
        assertTrue(thrownCards.get(0).cardType().isAction());
    }

    @Test
    public void assertNotInitialThrow() {
        setUp();
        thrownCards = new ArrayList<>(hand.throwCards());
        assertEquals(5, thrownCards.size());

        //after throwing, there shouldnt be any cards left in hand
        thrownCards = new ArrayList<>(thrownCards);
        assertEquals(0, thrownCards.size());

        int n = 50; //test count
        while (n --> 0) {
            hand.addCardsToHand(n);
            thrownCards = new ArrayList<>(hand.throwCards());
            assertTrue(thrownCards.get(0).cardType().isAction());
            assertEquals(n, thrownCards.size());
            for (int i = 1; i < n; i++) {
                assertFalse(thrownCards.get(i).cardType().isAction());
            }
            //throwing cards again shouldnt actually throw any cards
            assertEquals(0, hand.throwCards().size());
        }

        thrownCards = new ArrayList<>(hand.throwCards());
        //drawing 0 cards
        hand.addCardsToHand(0);
        thrownCards = new ArrayList<>(hand.throwCards());
        assertEquals(0, thrownCards.size());

    }

    @Test
    public void assertPlay() {
        setUp();

        Optional<CardInterface> cardToPlay = hand.play(0);
        assertTrue(cardToPlay.isPresent());
        assertTrue(cardToPlay.get().cardType().isAction());
        for (int i = 1; i < 5; i++) {
            cardToPlay = hand.play(i);
            assertTrue(cardToPlay.isPresent());
            assertFalse(cardToPlay.get().cardType().isAction());
        }

        assertEquals(0, hand.getCards().size());
        thrownCards = new ArrayList<>(hand.throwCards());
        assertEquals(0, thrownCards.size());
    }

    @Test
    public void assertEmptyHandPlay() {
        setUp();
        thrownCards = new ArrayList<>(hand.throwCards());
        assertEquals(0, hand.getCards().size());
        assertEquals(Optional.empty(), hand.play(0));
    }

    @Test
    public void assertCardExistAfterPlay() {
        setUp();
        assertTrue(hand.isCardInHand(5));
        assertTrue(hand.play(5).isPresent());
        assertFalse(hand.play(5).isPresent());
    }

}
