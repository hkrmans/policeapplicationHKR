package sample;

import java.sql.Date;

public class Conviction {
    private java.sql.Date release;
    private java.sql.Date conviction;
    private String sentence;
    private Prisoner prisoner;

    public java.sql.Date getRelease() {
        return release;
    }

    public Date getConviction() {
        return conviction;
    }

    public String getSentence() {
        return sentence;
    }

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public Conviction(Date release, Date conviction, String sentence, Prisoner prisoner) {
        this.release = release;
        this.conviction = conviction;
        this.sentence = sentence;
        this.prisoner = prisoner;
    }
}
