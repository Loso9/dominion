package sk.uniba.fmph.dcs;

import java.util.*;

public interface DeckInterface {
    List<CardInterface> draw(int count);
    List<CardInterface> getDeckOfCards();
    int getPoints();
}
