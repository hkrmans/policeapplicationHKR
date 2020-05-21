package sample.Models;

public class CrimeRapport implements Comparable<CrimeRapport>{
    private String rapport;
    private Person writer;
    private int rapportID;

    public CrimeRapport(String rapport, Person writer,int rapportID) {
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

    @Override
    public int compareTo(CrimeRapport o) {
        return (this.getWriter().getCivicNumber()) == o.getWriter().getCivicNumber() ? 0:1;
    }
}
