package sample;

import java.sql.*;
import java.util.*;

public class DbConnect<T>{
    private static DbConnect single_instance = null;
    private static Connection connection;

    public static DbConnect getInstance(){
        if (single_instance == null){
            try {
                single_instance = new DbConnect("Jb84raA1??10");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return single_instance;
    }

    private DbConnect(String password) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host","policemanagment" ,password);
    }

    public ArrayList<Civilian> getCivilians() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from person");
        ArrayList<Civilian> personList = new ArrayList<>();
        while(rs.next()){
            Civilian person = new Civilian(rs.getString(1),rs.getString(2),rs.getString(3));
            personList.add(person);
        }
        return personList;
    }

    public ArrayList<WantedCriminal> getWantedCriminals() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,WantedID,WantedRanking,Bounty from person,wantedcriminal where person.civicnumber = wantedcriminal.CivicNumber");
        ArrayList<WantedCriminal> personList = new ArrayList<>();
        while(rs.next()){
            WantedCriminal criminal = new WantedCriminal(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(6),rs.getInt(4));
            personList.add(criminal);
        }
        return personList;
    }

    public ArrayList<Police> getPolice() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,BadgeId from police,person where person.civicnumber = police.CivicNumber");
        ArrayList<Police> personList = new ArrayList<>();
        while(rs.next()){
            Police police = new Police(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            personList.add(police);
        }
        return personList;
    }

    public ArrayList<Prisoner> getPrisoner() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,PrisonerId from person,prisoners where person.civicnumber = prisoners.CivicNumber");
        ArrayList<Prisoner> personList = new ArrayList<>();
        while(rs.next()){
            Prisoner prisoner = new Prisoner(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),null);
            personList.add(prisoner);
        }
        return personList;
    }

    public void addPerson(Person person) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO person(firstName,lastName,civicnumber) values('"+person.getFirstName()+"','"+person.getLastName()+"','"+person.getCivicNumber()+"')");
        if(person instanceof Police){
            stmt.executeUpdate("INSERT INTO police(CivicNumber,BadgeId) values('"+((Police)person).getCivicNumber()+"','"+((Police)person).getBadgeId()+"')");
        }else if(person instanceof WantedCriminal){
            stmt.executeUpdate("INSERT INTO WantedCriminal(Bounty,WantedRanking,CivicNumber) values('"+((WantedCriminal)person).getBounty()+"','"+((WantedCriminal)person).getRanking()+"','"+((WantedCriminal)person).getCivicNumber()+"')");
        }else if(person instanceof Prisoner){
            stmt.executeUpdate("INSERT INTO prisoners(CivicNumber) values('"+((Prisoner)person).getCivicNumber()+"')");
        }
    }

    public static void addAccount(Account account) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO account(username,CivicNumber,password,email) values('"+account.getUsername()+"','"+((Person)account.getOwner()).getCivicNumber()+"','"+account.getPassword()+"','"+account.getEmail()+"')");
    }

    public ArrayList<Account> getAccount() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from person, account where person.civicnumber = account.CivicNumber");
        ArrayList<Account> accountList = new ArrayList<>();
        while(rs.next()){
            Person person = new Civilian(rs.getString(1),rs.getString(2),rs.getString(3));
            Account account = new Account(person,rs.getString(4),rs.getString(6),rs.getString(7));
            accountList.add(account);
        }
        return accountList;

    }

    public void addConviction(Conviction conviction) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO convictions(sentence,convictionDate,realeseDate,PrisonerID) values('"+conviction.getSentence()+"','"+conviction.getConviction()+"','"+conviction.getRelease()+"','"+((Prisoner)conviction.getPrisoner()).getPrisonerId()+"')");
    }

    public ArrayList<Conviction> getConviction() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from convictions, prisoners where convictions.PrisonerID = prisoners.PrisonerId");
        ArrayList<Conviction> convictionList = new ArrayList<>();
        while(rs.next()){
            Prisoner prisoner = new Prisoner(null,null,rs.getString(7),rs.getInt(6),null);
            Conviction conviction = new Conviction(rs.getDate(4),rs.getDate(3),rs.getString(1),prisoner);
            convictionList.add(conviction);
        }
        return convictionList;
    }

    public void bookMeeting(Meeting meeting) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO meeting(PrisonerID,person,scheduledDate) values('"+((Prisoner)meeting.getPrisoner()).getPrisonerId()+"','"+((Person)meeting.getVisitor()).getCivicNumber()+"','"+meeting.getDate()+"')");
    }

    public ArrayList<Meeting> getMeetings() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from prisoners,person,meeting where prisoners.PrisonerId = meeting.PrisonerID and meeting.person = person.CivicNumber");
        ArrayList<Meeting> meetingList = new ArrayList<>();
        while(rs.next()){
            Civilian person = new Civilian(rs.getString(3),rs.getString(4),rs.getString(5));
            Prisoner prisoner = new Prisoner(null,null,null,rs.getInt(1),null);
            Meeting meeting = new Meeting(prisoner,person,rs.getDate(8));
            meetingList.add(meeting);
        }
        return meetingList;
    }

    public void addCrime(Crime crime) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("insert into crime(dateOfCrime,typeOfCrime,suspect,raportID) values('"+crime.getDateOfCrime()+"','"+crime.getTypeOfCrime()+"','"+((Person)crime.getSuspect()).getCivicNumber()+"','"+((CrimeRapport)crime.getRapport()).getRapportID()+"')");
    }

    public ArrayList<Crime> getCrime() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from crime,crimeraport,wantedcriminal,person where wantedcriminal.CivicNumber = crime.suspect and crime.RaportID = crimeraport.RaportId and writter = person.civicnumber");
        ArrayList<Crime> crimesList = new ArrayList<>();
        while(rs.next()){
            Person writter = new Civilian(rs.getString(13),rs.getString(14),rs.getString(15));
            CrimeRapport raport = new CrimeRapport(rs.getString(7),writter,rs.getInt(4));
            WantedCriminal wantedCriminal = new WantedCriminal(null,null,rs.getString(11),rs.getInt(10),rs.getInt(9),rs.getInt(12));
            Crime crime = new Crime(rs.getDate(1),rs.getString(2),wantedCriminal,raport);
            crimesList.add(crime);
        }
        return crimesList;
    }
    public void addCrimeRapport(CrimeRapport rapport) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO crimeraport(Writter,Text) values('"+((Person)rapport.getWriter()).getCivicNumber()+"','"+rapport.getRapport()+"')");
    }

    public ArrayList<CrimeRapport> getCrimeRapport() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from person,crimeraport where person.civicnumber = writter");
        ArrayList<CrimeRapport> crimerapportList = new ArrayList<>();
        while(rs.next()){
            Civilian person = new Civilian(rs.getString(1),rs.getString(2),rs.getString(3));
            CrimeRapport rapport = new CrimeRapport(rs.getString(5),person,rs.getInt(6));
            crimerapportList.add(rapport);
        }
        return crimerapportList;
    }
}
