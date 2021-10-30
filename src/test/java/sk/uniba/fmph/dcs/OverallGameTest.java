package sk.uniba.fmph.dcs;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;


public class OverallGameTest {
    /*
     * This is a try to implement overall game test class
     *  - not sure if its needed after writing other tests, or whether this test is class is doing
     * what it is supposed to
     */

    /*
     * Will create few instances of games and track all steps
     */

    private Game game1;
    private Game game2;
    private Game game3;

    private List<BuyDeck> emptySupply;
    private List<BuyDeck> fullSupply;
    private List<BuyDeck> newCreatedSupply;

    private int currentPoints;
    private int currentActions;
    private int currentBuys;
    private int currentCoins;

    void setUp() {
        currentPoints = 0;
        currentCoins = 0;
        currentBuys = 0;
        currentActions = 0;
        emptySupply = new ArrayList<>();
        fullSupply = new ArrayList<>(DominionGame.supply);
        newCreatedSupply = new ArrayList<>();
        BuyDeck buyDeck1 = new BuyDeck(GameCardType.GAME_CARD_TYPE_PROVINCE, 5);
        BuyDeck buyDeck2 = new BuyDeck(GameCardType.GAME_CARD_TYPE_MARKET, 7);
        BuyDeck buyDeck3 = new BuyDeck(GameCardType.GAME_CARD_TYPE_LABORATORY, 3);
        BuyDeck buyDeck4 = new BuyDeck(GameCardType.GAME_CARD_TYPE_COPPER, 1);
        BuyDeck buyDeck5 = new BuyDeck(GameCardType.GAME_CARD_TYPE_ESTATE, 12);
        BuyDeck buyDeck6 = new BuyDeck(GameCardType.GAME_CARD_TYPE_VILLAGE, 8);
        BuyDeck buyDeck7 = new BuyDeck(GameCardType.GAME_CARD_TYPE_SMITHY, 6);
        addBuyDecks(newCreatedSupply, buyDeck1, buyDeck2, buyDeck3, buyDeck4, buyDeck5, buyDeck6, buyDeck7);

        AtleastNEmptyDecks atleast1EmptyDecks_1 = new AtleastNEmptyDecks(fullSupply, 1);
        AtleastNEmptyDecks atleast5EmptyDecks = new AtleastNEmptyDecks(emptySupply, 5);
        AtleastNEmptyDecks atleast1EmptyDecks_2 = new AtleastNEmptyDecks(newCreatedSupply, 1);

        game1 = new Game(atleast1EmptyDecks_1, fullSupply);
        game2 = new Game(atleast5EmptyDecks, emptySupply);
        game3 = new Game(atleast1EmptyDecks_2, newCreatedSupply);
    }

    public void addBuyDecks(List<BuyDeck> listOfBuyDecks, BuyDeck... buyDecks) {
        listOfBuyDecks.addAll(List.of(buyDecks));
    }

    public boolean isDeckWellInit(int emptyDecks, List<BuyDeck> supply) {
        Game newGame = new Game(new AtleastNEmptyDecks(supply, emptyDecks), supply);
        for (int i = 0; i < 7; i++) {
            if (!newGame.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER))) {
               return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (!newGame.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE))) {
                return false;
            }
        }
        return true;
    }

    public int numOfCardsInSupply(List<BuyDeck> supply) {
        int count = 0;
        for (BuyDeck buyDeck : supply) {
            count += buyDeck.size();
        }
        return count;
    }

    public void setStatus(int actions, int buys, int coins, int points) {
        currentActions = actions;
        currentBuys = buys;
        currentCoins = coins;
        currentPoints = points;
    }

    public int playCopper(Game game) {
        int count = 0;
        boolean condition = true;
        while (condition) {
            condition = game.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            if (condition) count++;

        }
        return count;
    }

    public int playEstate(Game game) {
        int count = 0;
        boolean condition = true;
        while (condition) {
            condition = game.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
            if (condition) count++;
        }
        return count;
    }

    @Test
    public void game1Test() {
        setUp();
        assertTrue(isDeckWellInit(1, fullSupply));
        //init
        assertTrue(game1.isActionPhase());

        setStatus(game1.getActions(), game1.getBuys(), game1.getCoins(), game1.getPoints());

        //no emptyDecks
        assertFalse(game2.isGameOver());
        assertEquals(76, numOfCardsInSupply(fullSupply));

        GameCard copperCard = new GameCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertFalse(copperCard.cardType().isAction());
        assertEquals(0, copperCard.cardType().getCost());
        assertEquals(0, copperCard.cardType().getPoints());
        assertEquals(0, copperCard.cardType().getPlusActions());
        assertEquals(0, copperCard.cardType().getPlusBuys());
        assertEquals(0, copperCard.cardType().getPlusCards());
        assertEquals(1, copperCard.cardType().getPlusCoins());

        GameCard estateCard = new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE);
        assertFalse(estateCard.cardType().isAction());
        assertEquals(2, estateCard.cardType().getCost());
        assertEquals(1, estateCard.cardType().getPoints());
        assertEquals(0, estateCard.cardType().getPlusActions());
        assertEquals(0, estateCard.cardType().getPlusBuys());
        assertEquals(0, estateCard.cardType().getPlusCards());
        assertEquals(0, estateCard.cardType().getPlusCoins());

        int coppersPlayed = playCopper(game1);
        int estatesPlayed = playEstate(game1);
        //currentActions - estatesPlayed should be > 0
        setStatus(currentActions - estatesPlayed, currentBuys, currentCoins + coppersPlayed, currentPoints + estatesPlayed);
        assertTrue(currentActions > 0);

        //after playing coppersPlayer coppers and estatesPlayed estates
        assertEquals(1 - estatesPlayed, game1.getActions());
        assertEquals(1, game1.getBuys());
        assertEquals(coppersPlayed, game1.getCoins());
        assertEquals(estatesPlayed, game1.getPoints());
        assertTrue(game1.endPlayCardPhase());

        switch (coppersPlayed) {
            case 0:
                game1.endTurn();
                break;
            case 1:
                game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
                setStatus(currentActions, currentBuys - 1, currentCoins - 1, currentPoints);
                assertEquals(currentActions, game1.getActions());
                assertEquals(currentBuys - 1, game1.getActions());
                assertEquals(currentCoins - 1, game1.getCoins());
                assertEquals(currentPoints, game1.getPoints());
                break;
            case 2:
                game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
                setStatus(currentActions, currentBuys - 1, currentCoins - 2, currentPoints + 1);
                assertEquals(currentActions, game1.getActions());
                assertEquals(currentBuys - 1, game1.getActions());
                assertEquals(currentCoins - 2, game1.getCoins());
                assertEquals(currentPoints + 1, game1.getPoints());
                break;
            case 3:
                game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
                setStatus(currentActions, currentBuys - 1, currentCoins - 3, currentPoints);
                assertEquals(currentActions, game1.getActions());
                assertEquals(currentBuys - 1, game1.getActions());
                assertEquals(currentCoins - 3, game1.getCoins());
                assertEquals(currentPoints, game1.getPoints());
                break;
            case 4:
                game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY));
                setStatus(currentActions, currentBuys - 1, currentCoins - 4, currentPoints);
                assertEquals(currentActions, game1.getActions());
                assertEquals(currentBuys - 1, game1.getActions());
                assertEquals(currentCoins - 4, game1.getCoins());
                assertEquals(currentPoints, game1.getPoints());
                break;

            case 5:
                game1.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
                setStatus(currentActions, currentBuys - 1, currentCoins - 5, currentPoints);
                assertEquals(currentActions, game1.getActions());
                assertEquals(currentBuys - 1, game1.getActions());
                assertEquals(currentCoins - 5, game1.getCoins());
                assertEquals(currentPoints, game1.getPoints());
                break;

        }

        assertFalse(game1.isGameOver());
        assertTrue(game1.endTurn());

        assertEquals(1, game1.getActions());
        assertEquals(1, game1.getBuys());
        assertEquals(0, game1.getCoins());
        assertEquals(currentPoints, game1.getPoints());

        //repeat - if there wasnt mistake in previous lines, im not going to continue with this test
        game1.setEndGame();

        //forcefully finished - obviously shouldnt be any winner
        assertTrue(game1.isGameOver());
        assertFalse(game1.wasWinnerFound());

        assertFalse(game1.playCard(0));
        assertFalse(game1.playCard(copperCard));
        assertFalse(game1.endPlayCardPhase());
        assertFalse(game1.buyCard(0));
        assertFalse(game1.buyCard(copperCard));
        assertFalse(game1.endTurn());

    }


    @Test
    public void game2Test() {
        setUp();
        //game starts with actionphase
        assertEquals(1, game2.getActions());
        assertEquals(1, game2.getBuys());
        assertEquals(0, game2.getCoins());
        assertEquals(0, game2.getPoints());
        assertTrue(game2.isActionPhase());

        //the fact, that all buyDecks are empty, game2.isGameOver() return true;
        // -> I shouldnt be able to play commands
        assertTrue(game2.isGameOver());
        assertEquals(0, numOfCardsInSupply(emptySupply));

        //can play cards because of starting deck containing 7 coppers and 3 estates, but because the game has
        //already ended, im shoudlnt be able to play them
        assertFalse(game2.playCard(0));
        assertFalse(game2.playCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertFalse(game2.endPlayCardPhase());
        assertFalse(game2.isActionPhase());
        assertFalse(game2.buyCard(0));
        assertFalse(game2.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER)));
        assertFalse(game2.endTurn());

        assertEquals(1, game2.getActions());
        assertEquals(1, game2.getBuys());
        assertEquals(0, game2.getCoins());

        //game was finished, but no winner
        assertEquals(0, game2.getPoints());
        assertTrue(game2.wasGameFinished());
        assertFalse(game2.wasWinnerFound());
    }

    @Test
    public void game3Test() {
        setUp();

        assertTrue(isDeckWellInit(1, newCreatedSupply));
        assertTrue(game3.isActionPhase());

        setStatus(game1.getActions(), game1.getBuys(), game1.getCoins(), game1.getPoints());

        //no emptyDecks
        assertFalse(game2.isGameOver());
        assertEquals(42, numOfCardsInSupply(newCreatedSupply));

        GameCard copperCard = new GameCard(GameCardType.GAME_CARD_TYPE_COPPER);
        assertFalse(copperCard.cardType().isAction());
        assertEquals(0, copperCard.cardType().getCost());
        assertEquals(0, copperCard.cardType().getPoints());
        assertEquals(0, copperCard.cardType().getPlusActions());
        assertEquals(0, copperCard.cardType().getPlusBuys());
        assertEquals(0, copperCard.cardType().getPlusCards());
        assertEquals(1, copperCard.cardType().getPlusCoins());

        GameCard estateCard = new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE);
        assertFalse(estateCard.cardType().isAction());
        assertEquals(2, estateCard.cardType().getCost());
        assertEquals(1, estateCard.cardType().getPoints());
        assertEquals(0, estateCard.cardType().getPlusActions());
        assertEquals(0, estateCard.cardType().getPlusBuys());
        assertEquals(0, estateCard.cardType().getPlusCards());
        assertEquals(0, estateCard.cardType().getPlusCoins());

        int coppersPlayed = playCopper(game1);
        int estatesPlayed = playEstate(game1);
        //currentActions - estatesPlayed should be > 0
        setStatus(currentActions - estatesPlayed, currentBuys, currentCoins + coppersPlayed, currentPoints + estatesPlayed);
        assertTrue(currentActions > 0);

        //after playing coppersPlayer coppers and estatesPlayed estates
        assertEquals(1 - estatesPlayed, game1.getActions());
        assertEquals(1, game1.getBuys());
        assertEquals(coppersPlayed, game1.getCoins());
        assertEquals(estatesPlayed, game1.getPoints());
        assertTrue(game1.endPlayCardPhase());

        switch (coppersPlayed) {
            case 0:
                game1.endTurn();
                break;
            case 1:

            case 2:

            case 3:

            case 4:

            case 5:
                game3.buyCard(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
                setStatus(currentActions, currentBuys - 1, currentCoins - 1, currentPoints);
                assertEquals(currentActions, game1.getActions());
                assertEquals(currentBuys - 1, game1.getActions());
                assertEquals(currentCoins - 1, game1.getCoins());
                assertEquals(currentPoints, game1.getPoints());
                assertTrue(game3.isGameOver());
                break;

        }
        //bought the only copper left, got 1 empty buyDeck -> gameover
        if (numOfCardsInSupply(newCreatedSupply) == 41) {
            assertTrue(game3.isGameOver());
        }
        assertFalse(game3.isGameOver());
    }

}
