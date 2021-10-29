package sk.uniba.fmph.dcs;

import org.junit.*;
import static org.junit.Assert.*;

public class GameCardTest {

    /*
     * Tests needed:
     * Is card correctly evaluated
     */

    private GameCard gameCard1;
    private GameCard gameCard2;
    private GameCard gameCard3;

    private void setUp() {
        gameCard1 = new GameCard(GameCardType.GAME_CARD_TYPE_COPPER);
        gameCard2 = new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE);
        gameCard3 = new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL);

    }

    private void assertCardType(GameCard gameCard1, GameCard gameCard2) {
        assertEquals(gameCard1, gameCard2);
    }

    private void assertTurnStatus(TurnStatus ts, int actions, int buys, int coins) {
        assertEquals(actions, ts.getActions());
        assertEquals(buys, ts.getBuys());
        assertEquals(coins, ts.getCoins());
    }

    @Test
    public void gameCardTest() {
        setUp();
        TurnStatus ts = new TurnStatus();
        ts.setActions(0);
        ts.setBuys(0);
        ts.setCoins(0);

        assertCardType(gameCard1, new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        assertCardType(gameCard2, new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));

        int plusCardsCOPPER = GameCardType.GAME_CARD_TYPE_COPPER.getPlusCards();
        int plusCardsESTATE = GameCardType.GAME_CARD_TYPE_ESTATE.getPlusCards();


        int cardEvaluated1 = gameCard1.evaluate(ts);
        int cardEvaluated2 = gameCard2.evaluate(ts);

        int plusActionsCOPPER = GameCardType.GAME_CARD_TYPE_COPPER.getPlusActions();
        int plusBuysCOPPER = GameCardType.GAME_CARD_TYPE_COPPER.getPlusBuys();
        int plusCoinsCOPPER = GameCardType.GAME_CARD_TYPE_COPPER.getPlusCoins();

        int plusActionsESTATE = GameCardType.GAME_CARD_TYPE_ESTATE.getPlusActions();
        int plusBuysESTATE = GameCardType.GAME_CARD_TYPE_ESTATE.getPlusBuys();
        int plusCoinsESTATE = GameCardType.GAME_CARD_TYPE_ESTATE.getPlusCoins();

        assertEquals(cardEvaluated1, plusCardsCOPPER);
        assertEquals(cardEvaluated2, plusCardsESTATE);

        assertTurnStatus(ts, plusActionsCOPPER + plusActionsESTATE, plusBuysCOPPER + plusBuysESTATE, plusCoinsCOPPER + plusCoinsESTATE);

        //mid-turn card evaluation
        TurnStatus newTurnStatus = new TurnStatus();
        int startCoins = 4;
        int startBuys = 2;
        int startActions = 3;
        newTurnStatus.setCoins(startCoins);
        newTurnStatus.setBuys(startBuys);
        newTurnStatus.setActions(startActions);

        assertCardType(gameCard3, new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        int festivalEvaluated = gameCard3.evaluate(newTurnStatus);
        int plusCardsFESTIVAL = GameCardType.GAME_CARD_TYPE_FESTIVAL.getPlusCards();

        assertEquals(festivalEvaluated, plusCardsFESTIVAL);

        int plusActionsFESTIVAL = GameCardType.GAME_CARD_TYPE_FESTIVAL.getPlusActions();
        int plusBuysFESTIVAL = GameCardType.GAME_CARD_TYPE_ESTATE.getPlusBuys();
        int plusCoinsFESTIVAL = GameCardType.GAME_CARD_TYPE_ESTATE.getPlusCoins();

        assertTurnStatus(ts, plusActionsFESTIVAL + startActions, plusBuysFESTIVAL + startBuys, plusCoinsFESTIVAL + startCoins);

    }
}
