package sample;

import java.util.Date;

public class Meeting {
    private Prisoner prisoner;
    private Civilian visitor;
    private Date date;

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public Civilian getVisitor() {
        return visitor;
    }

    public Date getDate() {
        return date;
    }

    public Meeting(Prisoner prisoner, Civilian visitor, Date date) {
        this.prisoner = prisoner;
        this.visitor = visitor;
        this.date = date;
    }
}
