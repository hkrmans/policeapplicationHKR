package sample.Models;

public class WantedCriminal extends Person {
    private int ranking;
    private int bounty;
    private int wantedId;

    public WantedCriminal(String firstName, String lastName, String civicNumber, int ranking, int bounty,int wantedId) {
        super(firstName, lastName, civicNumber);
        this.bounty = bounty;
        this.ranking = ranking;
        this.wantedId = wantedId;
    }

    public int getRanking() {
        return ranking;
    }

    public int getBounty() {
        return bounty;
    }

    public int getWantedId(){
        return wantedId;
    }

}
