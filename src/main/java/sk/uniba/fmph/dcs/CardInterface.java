package sk.uniba.fmph.dcs;

import java.util.*;

interface CardInterface {
    Map.Entry<Integer, CardInterface> evaluate(TurnStatus ts);
    GameCardType cardType();
}


