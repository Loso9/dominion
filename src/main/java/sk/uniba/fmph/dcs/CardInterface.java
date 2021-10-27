package sk.uniba.fmph.dcs;

import java.util.*;

interface CardInterface {
    int evaluate(TurnStatus ts);
    GameCardType cardType();
}


