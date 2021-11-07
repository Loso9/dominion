package sk.uniba.fmph.dcs;

import java.util.*;

public class Shuffler {

    private final List<CardInterface> cards;

    public Shuffler(List<CardInterface> cards) {
        this.cards = cards;
    }
    public List<CardInterface> shuffle() {
        Random rnd = new Random(System.currentTimeMillis());
        LCGGenerator generator = new LCGGenerator(rnd.nextLong(), rnd.nextLong(),
                rnd.nextLong(), rnd.nextLong());
        for (int i = 0; i < cards.size() * 5; i++) {
            int indexFrom = generateRandomIndex(generator);
            int indexWhere = generateRandomIndex(generator);
            //swap
            CardInterface tempCard = cards.get(indexFrom);
            cards.set(indexFrom, cards.get(indexWhere));
            cards.set(indexWhere, tempCard);

        }
        return cards;
    }

    private int generateRandomIndex(LCGGenerator generator) {
        return (int) Math.abs(generator.nextLong() % cards.size());
    }
}
