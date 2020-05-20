package sample.Models;

public class CrimeReport {
    private String rapport;
    private Person writer;
    private int rapportID;

    public CrimeReport(String rapport, Person writer, int rapportID) {
        this.rapport = rapport;
        this.writer = writer;
        this.rapportID = rapportID;

    }

    public String getRapport() {
        return rapport;
    }

    public Person getWriter() {
        return writer;
    }

    public int getRapportID(){
        return rapportID;
    }
}
