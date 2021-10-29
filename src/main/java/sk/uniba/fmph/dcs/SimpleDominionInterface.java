package sk.uniba.fmph.dcs;

import java.util.Optional; 
 
interface SimpleDominionInterface {
    Optional<GameState> playCard(int handIdx);
    Optional<GameState> buyCard(CardInterface card);
    Optional<GameState> endTurn();
}
