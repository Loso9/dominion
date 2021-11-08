package sk.uniba.fmph.dcs;

public class LCGGenerator {

    /*
     * Linear congruential generator for pseudo-random numbers
     * - usage: Shuffler
     */

    private long lastNumber;
    private final long a;
    private final long c;
    private final long m;

    public LCGGenerator(long a, long c, long m, long seed) {
        lastNumber = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public long nextLong() {
        long next = (a * lastNumber + c) % m;
        lastNumber = next;
        return next;
    }
}
