package sample;

public class Police extends Person {
    private int badgeId;

    public Police(String firstName, String lastName, String civicNumber, int badgeId) {
        super(firstName, lastName, civicNumber);
        this.badgeId = badgeId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }
}
