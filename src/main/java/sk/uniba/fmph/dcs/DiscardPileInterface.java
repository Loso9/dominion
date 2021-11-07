package sk.uniba.fmph.dcs;

import java.util.*;

public interface DiscardPileInterface {
    Optional<CardInterface> getTopCard();
    void addCards(List<CardInterface> cards);
    void addCard(CardInterface card);
    List<CardInterface> getShuffledCards(); //for nonshufflingdiscardpile returns getCards();
    List<CardInterface> getCards();
    int getSize();
    int getPoints();
}
