package sample.Models;

import sample.Models.Person;

public class Police extends Person implements Comparable<Police>{
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

    @Override
    public int compareTo(Police o) {
        return (Integer.parseInt(this.getBadgeId()) < Integer.parseInt(o.getBadgeId()) ? 0:1);
    }
}
