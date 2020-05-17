package sample;

import java.util.Date;

public class Meeting {
    private Prisoner prisoner;
    private Civilian visitor;
    private java.sql.Date date;

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public Civilian getVisitor() {
        return visitor;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public Meeting(Prisoner prisoner, Civilian visitor, java.sql.Date date) {
        this.prisoner = prisoner;
        this.visitor = visitor;
        this.date = date;
    }

    public void setPrisoner(Prisoner prisoner){
        this.prisoner = prisoner;
    }
}
