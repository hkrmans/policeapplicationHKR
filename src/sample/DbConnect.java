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
            WantedCriminal criminal = new WantedCriminal(rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(5),rs.getInt(6));
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
        ResultSet rs = stmt.executeQuery("select * from account");
        ArrayList<Account> accountList = new ArrayList<>();
        while(rs.next()){
            ResultSet rs2 = stmt.executeQuery("show tables");
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
        ResultSet rs = stmt.executeQuery("select * from convictions");
        ArrayList<Conviction> convictionList = new ArrayList<>();
        while(rs.next()){
            Conviction conviction = new Conviction(rs.getDate(4),rs.getDate(3),rs.getString(1),null);
            ResultSet rs2 = stmt.executeQuery("select * from person where person.civi" + rs.getString(5));
            convictionList.add(conviction);
        }
        return convictionList;
    }

    public void bookMeeting(Meeting meeting) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO meeting(PrisonerID,person,scheduleDate) values('"+((Prisoner)meeting.getPrisoner()).getPrisonerId()+"','"+((Person)meeting.getVisitor()).getCivicNumber()+"','"+meeting.getDate()+"')");
    }

    public ArrayList<Meeting> getMeetings() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from meeting");
        ArrayList<Meeting> meetingList = new ArrayList<>();
        while(rs.next()){
            ResultSet rs2 = stmt.executeQuery("select * from person where person.civicnumber = " + rs.getString(2));
            Civilian person = new Civilian(rs2.getString(1),rs2.getString(2),rs2.getString(3));
            Meeting meeting = new Meeting(null,person,rs.getDate(3));
        }
        return meetingList;
    }

    public void addCrime(Crime crime) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO crime(TypeOfCrime,dateOfCrime,suspect,RaportId) values('"+crime.getTypeOfCrime()+"','"+crime.getDateOfCrime()+"','"+((Person)crime.getSuspect()).getCivicNumber()+"','"+((CrimeRapport)crime.getRapport()).getRapportID()+"')");
    }

    public ArrayList<Crime> getCrime() throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from crime");
        ArrayList<Crime> crimesList = new ArrayList<>();
        while(rs.next()){
            ResultSet rs2 = stmt.executeQuery("select * from person where person.civicnumber = " + rs.getString(3));
            Person person = new Civilian(rs2.getString(1), rs2.getString(2),rs2.getString(3));
            ResultSet rs3 = stmt.executeQuery("select * from crimeraport where crimeraport.RaportId = " +rs.getString(4));
            ResultSet rs4 = stmt.executeQuery("select * from person where person.civicnumber = " + rs3.getString(1));
            Person person2 = new Civilian(rs4.getString(1),rs4.getString(2),rs4.getString(3));
            CrimeRapport raport = new CrimeRapport(rs3.getString(2),person2,rs.getInt(4));
            Crime crime = new Crime(rs.getDate(2),rs.getString(1),person,raport);
            crimesList.add(crime);
        }
        return crimesList;
    }
    public void addCrimeRapport(CrimeRapport rapport) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        stmt.executeUpdate("INSERT INTO crimeraport(Writter,Text) values('"+((Person)rapport.getWriter()).getCivicNumber()+"','"+rapport.getRapport()+"')");
    }
}
