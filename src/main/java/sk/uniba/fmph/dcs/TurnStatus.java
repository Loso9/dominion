package sk.uniba.fmph.dcs;

public class TurnStatus {
    private int actions;
    private int buys;
    private int coins;
    private int points;

    public TurnStatus() {
        setPoints(0);
        setActions(1);
        setCoins(0);
        setBuys(1);
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

    public void setActions(int actions) {
        this.actions = actions;
    }

    public void setBuys(int buys) {
        this.buys = buys;

    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
