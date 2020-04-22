package sample;

public class CrimeRapport {
    private String rapport;
    private Person writer;

    public CrimeRapport(String rapport, Person writer) {
        this.rapport = rapport;
        this.writer = writer;
    }

    public String getRapport() {
        return rapport;
    }

    public Person getWriter() {
        return writer;
    }
}
