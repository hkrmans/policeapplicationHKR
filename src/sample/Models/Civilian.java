package sample.Models;

public class Civilian extends Person implements Comparable<Person> {
    public Civilian(String firstName, String lastName, String civicNumber) {
        super(firstName, lastName, civicNumber);
    }

    @Override
    public int compareTo(Person o) {
        return (Integer.parseInt(this.getCivicNumber()) < Integer.parseInt(o.getCivicNumber())? 0:1);
    }
}
