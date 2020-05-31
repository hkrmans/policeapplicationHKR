package sample.Models;

import sample.Models.Person;

import java.util.Date;

public class Prisoner extends Person implements Comparable<Prisoner>{
    private Date releaseDate;
    private int prisonerId;
    public Prisoner(String firstName, String lastName, String civicNumber, int prisonerId, Date releaseDate) {
        super(firstName, lastName, civicNumber);
        this.prisonerId = prisonerId;
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPrisonerId() {
        return prisonerId;
    }

    @Override
    public int compareTo(Prisoner o) {
        return 0;
    }
}