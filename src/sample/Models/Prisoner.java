package sample.Models;

public class Prisoner extends Person {
    private int prisonerId;
    public Prisoner(String firstName, String lastName, String civicNumber, int prisonerId) {
        super(firstName, lastName, civicNumber);
        this.prisonerId = prisonerId;
    }

    public int getPrisonerId() {
        return prisonerId;
    }

}
