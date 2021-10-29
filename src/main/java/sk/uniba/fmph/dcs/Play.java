package sk.uniba.fmph.dcs;

import java.util.*;

public class Play {

    private final ArrayList<CardInterface> cardsInPlay;

    public Play() {
        cardsInPlay = new ArrayList<>();
    }

    public void putTo(CardInterface card) {
        assert card != null;
        cardsInPlay.add(card);
    }

    public List<CardInterface> throwAll() {
        ArrayList<CardInterface> returnCards = new ArrayList<>(cardsInPlay);
        cardsInPlay.clear();
        return returnCards;
    }

}
