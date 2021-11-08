package sk.uniba.fmph.dcs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {

    private Game game1;
    private Game game2;
    private Game game3;

    private void setUp() {
        ArrayList<BuyDeck> supply1 = new ArrayList<>();
        ArrayList<BuyDeck> supply2 = new ArrayList<>();
        ArrayList<BuyDeck> supply3 = new ArrayList<>();
        BuyDeck estate = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 5);
        BuyDeck copper = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1);
        BuyDeck festival = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 7);
        BuyDeck provinceForGame2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 2);
        supply2.add(estate);
        supply2.add(copper);
        supply2.add(festival);
        supply2.add(provinceForGame2);

        BuyDeck provinceForGame3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 0);
        supply3.add(provinceForGame3);

        game1 = new Game(supply1, true, new AtleastNEmptyDecks(supply1, 1));
        game2 = new Game(supply2, true, new AtleastNEmptyDecks(supply2, 1));
        game3 = new Game(supply3, true, new EmptyProvinceDeck(supply3.get(0)));
    }

    @Test
    public void gameTest() {
        setUp();
        //not deterministic
        //assertTrue(game1.playCard(0));
        //assertTrue(game2.playCard(0));

        //shouldnt be able to buy in actionphase or the the gameover method inside the game class returns true (atleast one empty deck)
        assertFalse(game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));
        assertFalse(game2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertFalse(game3.buyCard(0));


        assertTrue(game2.endPlayCardPhase());
        assertFalse(game3.endPlayCardPhase());

        assertFalse(game2.endPlayCardPhase());
        assertFalse(game3.endPlayCardPhase());

        assertTrue(game2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));

        //empty supply
        assertFalse(game1.buyCard(0));
        assertFalse(game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));

        assertTrue(game1.endTurn());
        //already gameover
        assertFalse(game2.endTurn());
        assertFalse(game3.endTurn());

        assertFalse(game1.isGameOver());
        assertTrue(game2.isGameOver());
        assertTrue(game3.isGameOver());


        assertFalse(game2.playCard(0));
        assertFalse(game2.endPlayCardPhase());
        assertFalse(game2.buyCard(0));
        assertFalse(game2.endTurn());

        assertFalse(game3.playCard(0));
        assertFalse(game3.endPlayCardPhase());
        assertFalse(game3.buyCard(0));
        assertFalse(game3.endTurn());

    }

    @Test
    public void assertEndGame() {
        setUp();
        game1.endTurn();
        game2.endTurn();

        assertFalse(game1.wasGameFinished());
        assertFalse(game2.wasGameFinished());

        assertFalse(game1.wasWinnerFound());
        assertFalse(game2.wasWinnerFound());

    }

}
