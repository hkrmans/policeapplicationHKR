package sample;

public class WantedCriminal extends Person {
    private int ranking;
    private double bounty;

    public WantedCriminal(String firstName, String lastName, String civicNumber, int ranking, double bounty) {
        super(firstName, lastName, civicNumber);
        this.bounty = bounty;
        this.ranking = ranking;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }
}
