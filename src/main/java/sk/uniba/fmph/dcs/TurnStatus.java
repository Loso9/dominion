package sk.uniba.fmph.dcs;

public class TurnStatus {
    private int actions;
    private int buys;
    private int coins;

    public TurnStatus() {
        this.actions = 0;
        this.buys = 0;
        this.coins = 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + actions;
        result = prime * result + buys;
        result = prime * result + coins;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TurnStatus other = (TurnStatus) obj;
        if (actions != other.actions)
            return false;
        if (buys != other.buys)
            return false;
        return coins == other.coins;
    }

    public int getActions() {
        return actions;
    }

    public void addActions(int actions) {
        this.actions += actions;
    }

    public int getBuys() {
        return buys;
    }

    public void addBuys(int buys) {
        this.buys += buys;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }
}
