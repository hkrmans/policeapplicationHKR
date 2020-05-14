package sample;

public class CrimeRapport {
    private String rapport;
    private Person writer;
    private int rapportID;

    public CrimeRapport(String rapport, Person writer,int rapportID) {
        this.rapport = rapport;
        this.writer = writer;
        this.writer = writer;

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
