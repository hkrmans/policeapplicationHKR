package sample;

public class Police extends Person {
    private String badgeId;

    public Police(String firstName, String lastName, String civicNumber, String badgeId) {
        super(firstName, lastName, civicNumber);
        this.badgeId = badgeId;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }
}
