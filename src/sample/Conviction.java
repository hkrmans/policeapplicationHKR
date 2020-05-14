package sample;

import java.sql.Date;

public class Conviction {
    private Date release;
    private Date conviction;
    private String sentence;
    private Prisoner prisoner;

    public Date getRelease() {
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
