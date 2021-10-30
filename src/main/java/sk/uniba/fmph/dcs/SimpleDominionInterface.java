package sk.uniba.fmph.dcs;

import java.util.Optional; 
 
interface SimpleDominionInterface {
    Optional<GameState> playCard(int handIdx);
    Optional<GameState> playCard(CardInterface card);
    Optional<GameState> buyCard(int buyIdx);
    Optional<GameState> buyCard(CardInterface card);
    Optional<GameState> endTurn();
    Optional<GameState> endPlayCardPhase();
}
