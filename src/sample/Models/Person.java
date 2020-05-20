package sample.Models;

public abstract class Person {
    private String firstName;
    private String lastName;
    private String civicNumber;

    public Person(String firstName, String lastName, String civicNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.civicNumber = civicNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCivicNumber() {
        return civicNumber;
    }

}
