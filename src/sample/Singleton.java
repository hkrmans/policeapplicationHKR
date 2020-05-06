package sample;

import java.sql.SQLException;
import java.util.ArrayList;

public class Singleton {
    private DbConnect dbc;

    {
        try {
            dbc = new DbConnect("policemanagment", "Jb84raA1??10");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Singleton(int nbr) {
        switch (nbr) {
            case 1:
                civilianList = new ArrayList<>();
                break;
            case 2:
                policeList = new ArrayList<>();
                break;
            case 3:
                prisonerList = new ArrayList<>();
                break;
            case 4:
                wantedCriminalsList = new ArrayList<>();
                break;
            case 5:
                crimeList = new ArrayList<>();
                break;
            case 6:
                crimeRapportList = new ArrayList<>();
                break;
            case 7:
                meetingList = new ArrayList<>();
                break;
            case 8:
                accountList = new ArrayList<>();
        }
    }

    private static Singleton civilianInstance;
    private ArrayList<Civilian> civilianList = null;

    public static Singleton getCivilianInstance() {
        if (civilianInstance == null) {
            civilianInstance = new Singleton(8);
        }
        return civilianInstance;
    }

    public ArrayList<Civilian> getCivilianList() throws SQLException {
        return dbc.getCivilians();
    }
    //---------------------------------------------------------------------------------------------
    private static Singleton policeInstance;
    private ArrayList<Police> policeList = null;

    public static Singleton getPoliceInstance() {
        if (policeInstance == null) {
            policeInstance = new Singleton(2);
        }
        return policeInstance;
    }

    public ArrayList<Police> getPoliceList() throws SQLException {
        return dbc.getPolice();
    }

    //---------------------------------------------------------------------------------------------
    private static Singleton prisonerInstance;
    private ArrayList<Prisoner> prisonerList = null;

    public static Singleton getPrisonerInstance() {
        if (prisonerInstance == null) {
            prisonerInstance = new Singleton(3);
        }
        return prisonerInstance;
    }

    public ArrayList<Prisoner> getPrisonerList() throws SQLException {
        return dbc.getPrisoner();
    }

    //---------------------------------------------------------------------------------------------
    private static Singleton wantedCriminalInstance;
    private ArrayList<WantedCriminal> wantedCriminalsList = null;

    public static Singleton getWantedCriminalInstance() {
        if (wantedCriminalInstance == null) {
            wantedCriminalInstance = new Singleton(4);
        }
        return wantedCriminalInstance;
    }

    public ArrayList<WantedCriminal> getWantedCriminalList() throws SQLException {
        return dbc.getWantedCriminals();
    }

    //---------------------------------------------------------------------------------------------
    private static Singleton crimeInstance;
    private ArrayList<Crime> crimeList = null;

    public static Singleton getCrimeInstance() {
        if (crimeInstance == null) {
            crimeInstance = new Singleton(5);
        }
        return crimeInstance;
    }

    public ArrayList<Crime> getCrimeList() {
        return this.crimeList;
    }

    //---------------------------------------------------------------------------------------------
    private static Singleton crimeRapportInstance;
    private ArrayList<CrimeRapport> crimeRapportList = null;

    public static Singleton getCrimeRapportInstance() {
        if (crimeRapportInstance == null) {
            crimeRapportInstance = new Singleton(6);
        }
        return crimeRapportInstance;
    }

    public ArrayList<CrimeRapport> getCrimeRapportList() {
        return this.crimeRapportList;
    }

    //---------------------------------------------------------------------------------------------
    private static Singleton meetingInstance;
    private ArrayList<Meeting> meetingList = null;

    public static Singleton getMeetingInstance() {
        if (meetingInstance == null) {
            meetingInstance = new Singleton(7);
        }
        return meetingInstance;
    }

    public ArrayList<Meeting> getMeetingList() {
        return this.meetingList;
    }

    //---------------------------------------------------------------------------------------------
    private static Singleton accountInstance;
    private ArrayList<Account> accountList = null;

    public static Singleton getAccountInstance() {
        if (accountInstance == null) {
            accountInstance = new Singleton(8);
        }
        return accountInstance;
    }

    public ArrayList<Account> getAccountList() {
        return this.accountList;
    }

    //---------------------------------------------------------------------------------------------
}

