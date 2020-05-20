package sample.Models;

public class Meeting {
    private Prisoner prisoner;
    private Person visitor;
    private java.sql.Date date;
    private int meetingID;

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public Person getVisitor() {
        return visitor;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public Meeting(Prisoner prisoner, Person visitor, java.sql.Date date,int meetingID) {
        this.prisoner = prisoner;
        this.visitor = visitor;
        this.date = date;
        this.meetingID = meetingID;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setPrisoner(Prisoner prisoner){
        this.prisoner = prisoner;
    }
}
