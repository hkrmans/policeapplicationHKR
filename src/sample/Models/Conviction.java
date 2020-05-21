package sample.Models;

import java.sql.Date;

public class Conviction implements Comparable<Conviction>{
    private java.sql.Date release;
    private java.sql.Date conviction;
    private String sentence;
    private Prisoner prisoner;
    private int convictionID;

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

    public int getConvictionID() {
        return convictionID;
    }

    public Conviction(Date release, Date conviction, String sentence, Prisoner prisoner, int convictionID) {
        this.release = release;
        this.conviction = conviction;
        this.sentence = sentence;
        this.prisoner = prisoner;
        this.convictionID = convictionID;
    }

    @Override
    public int compareTo(Conviction o) {
        return (this.getPrisoner().getPrisonerId() < o.getPrisoner().getPrisonerId() ? 0:1);
    }
}
