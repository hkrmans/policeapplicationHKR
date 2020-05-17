package sample;

import java.sql.Date;

public class Crime {
    private java.sql.Date dateOfCrime;
    private String typeOfCrime;
    private Person suspect;
    private CrimeRapport rapport;

    public Crime(java.sql.Date dateOfCrime, String typeOfCrime, Person suspect, CrimeRapport rapport) {
        this.dateOfCrime = dateOfCrime;
        this.typeOfCrime = typeOfCrime;
        this.suspect = suspect;
        this.rapport = rapport;
    }

    public java.sql.Date getDateOfCrime() {
        return dateOfCrime;
    }

    public String getTypeOfCrime() {
        return typeOfCrime;
    }

    public Person getSuspect() {
        return suspect;
    }

    public CrimeRapport getRapport() {
        return rapport;
    }
}
