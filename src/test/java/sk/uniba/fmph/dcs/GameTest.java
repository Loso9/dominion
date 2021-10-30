package sk.uniba.fmph.dcs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GameTest {

    private Game game1;
    private Game game2;
    private Game game3;
    private Game gameAlreadyFinished;

    private void setUp() {
        ArrayList<BuyDeck> supply1 = new ArrayList<>();
        ArrayList<BuyDeck> supply2 = new ArrayList<>();
        BuyDeck estate = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 5);
        BuyDeck copper = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1);
        BuyDeck festival = new BuyDeck(GameCardType.GAME_CARD_TYPE_FESTIVAL, 7);
        supply2.add(estate);
        supply2.add(copper);
        supply2.add(festival);
        ArrayList<BuyDeck> supply3 = new ArrayList<>(DominionGame.supply);

        game1 = new Game(new AtleastNEmptyDecks(supply1, 1), supply1);
        game2 = new Game(new AtleastNEmptyDecks(supply2, 2), supply2);
        game3 = new Game(new AtleastNEmptyDecks(supply3, 3), supply3);
        gameAlreadyFinished = new Game(new AtleastNEmptyDecks(supply3, 0), supply3);
    }

    @Test
    public void gameTest() {
        setUp();
        //no smithy in init deck
        assertTrue(game1.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));
        assertTrue(game2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertFalse(game3.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY)));

        //shouldnt be able to buy in actionphase
        assertFalse(game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE)));
        assertFalse(game2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertFalse(game3.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));

        //switch to buyPhase
        assertTrue(game1.endPlayCardPhase());
        assertTrue(game2.endPlayCardPhase());
        assertTrue(game3.endPlayCardPhase());

        assertFalse(game1.endPlayCardPhase());
        assertFalse(game2.endPlayCardPhase());
        assertFalse(game3.endPlayCardPhase());

        assertTrue(game2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));

        assertTrue(game1.endTurn());
        assertTrue(game2.endTurn());
        assertTrue(game3.endTurn());

        assertFalse(game1.isGameOver());
        assertTrue(game2.isGameOver());
        assertFalse(game3.isGameOver());

        //shouldnt be able to use commands when game ended
        assertFalse(game2.playCard(0));
        assertFalse(game2.endPlayCardPhase());
        assertFalse(game2.buyCard(0));
        assertFalse(game2.endTurn());

    }

    @Test
    public void assertEndGame() {
        setUp();
        game1.endTurn();
        game2.endTurn();
        game3.endTurn();
        gameAlreadyFinished.endTurn();

        assertFalse(game1.wasGameFinished());
        assertFalse(game2.wasGameFinished());
        assertFalse(game3.wasGameFinished());
        assertTrue(gameAlreadyFinished.wasGameFinished());

        assertFalse(game1.wasWinnerFound());
        assertFalse(game2.wasWinnerFound());
        assertFalse(game3.wasWinnerFound());
        assertFalse(gameAlreadyFinished.wasWinnerFound());

    }

}
