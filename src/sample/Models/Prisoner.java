package sample.Models;

import sample.Models.Person;

import java.util.Date;

public class Prisoner extends Person implements Comparable<Prisoner>{
    private int prisonerId;
    public Prisoner(String firstName, String lastName, String civicNumber, int prisonerId) {
        super(firstName, lastName, civicNumber);
        this.prisonerId = prisonerId;

    }

    public int getPrisonerId() {
        return prisonerId;
    }

    @Override
    public int compareTo(Prisoner o) {
        return 0;
    }
}
