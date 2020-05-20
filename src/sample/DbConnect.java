package sample;

import com.mysql.cj.result.SqlDateValueFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DbConnect<T>{
    private static DbConnect single_instance = null;
    private static Connection connection;
    private static Sec sec = new Sec();

    public static DbConnect getInstance(String password){
        if (single_instance == null){
            try {
                single_instance = new DbConnect(password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return single_instance;
    }

    private DbConnect(String password) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host","policemanagment" ,password);
    }

    public ArrayList<Civilian> getCivilians(){
        ArrayList<Civilian> personList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select * from person");
            while (rs.next()) {
                Civilian person = new Civilian(rs.getString(1), rs.getString(2), rs.getString(3));
                personList.add(person);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return personList;
    }

    public ArrayList<WantedCriminal> getWantedCriminals(){
        ArrayList<WantedCriminal> personList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,WantedID,WantedRanking,Bounty from person,wantedcriminal where person.civicnumber = wantedcriminal.CivicNumber");
            while (rs.next()) {
                WantedCriminal criminal = new WantedCriminal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(6), rs.getInt(4));
                personList.add(criminal);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return personList;
    }

    public ArrayList<Police> getPolice(){
        ArrayList<Police> personList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,BadgeId from police,person where person.civicnumber = police.CivicNumber");
            while (rs.next()) {
                Police police = new Police(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                personList.add(police);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return personList;
    }

    public ArrayList<Prisoner> getPrisoner(){
        ArrayList<Prisoner> personList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,PrisonerId from person,prisoners where person.civicnumber = prisoners.CivicNumber");
            while (rs.next()) {
                Prisoner prisoner = new Prisoner(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), null);
                personList.add(prisoner);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return personList;
    }

    public void addPerson(Person person){
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            stmt.executeUpdate("INSERT INTO person(firstName,lastName,civicnumber) values('" + person.getFirstName() + "','" + person.getLastName() + "','" + person.getCivicNumber() + "')");
            if (person instanceof Police) {
                stmt.executeUpdate("INSERT INTO police(CivicNumber,BadgeId) values('" + ((Police) person).getCivicNumber() + "','" + ((Police) person).getBadgeId() + "')");
            } else if (person instanceof WantedCriminal) {
                stmt.executeUpdate("INSERT INTO WantedCriminal(Bounty,WantedRanking,CivicNumber) values('" + ((WantedCriminal) person).getBounty() + "','" + ((WantedCriminal) person).getRanking() + "','" + ((WantedCriminal) person).getCivicNumber() + "')");
            } else if (person instanceof Prisoner) {
                stmt.executeUpdate("INSERT INTO prisoners(CivicNumber) values('" + ((Prisoner) person).getCivicNumber() + "')");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addAccount(Account account) throws NoSuchAlgorithmException {
        try{
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            stmt.executeUpdate("INSERT INTO account(username,CivicNumber,password,email) values('"+account.getUsername()+"','"+((Person)account.getOwner()).getCivicNumber()+"','"+sec.hashPassword(account.getPassword())+"','"+account.getEmail()+"')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Account> getAccount(){
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select * from person, account where person.civicnumber = account.CivicNumber");
            while (rs.next()) {
                Person person = new Civilian(rs.getString(1), rs.getString(2), rs.getString(3));
                Account account = new Account(person, rs.getString(4), rs.getString(6), rs.getString(7));
                accountList.add(account);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountList;
    }

    public void addConviction(Conviction conviction){
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            stmt.executeUpdate("INSERT INTO convictions(sentence,convictionDate,realeseDate,PrisonerID) values('" + conviction.getSentence() + "','" + conviction.getConviction() + "','" + conviction.getRelease() + "','" + ((Prisoner) conviction.getPrisoner()).getPrisonerId() + "')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Conviction> getConviction(){
        ArrayList<Conviction> convictionList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select * from convictions, prisoners where convictions.PrisonerID = prisoners.PrisonerId");
            while (rs.next()) {
                Prisoner prisoner = new Prisoner(null, null, rs.getString(7), rs.getInt(6), null);
                Conviction conviction = new Conviction(rs.getDate(4), rs.getDate(3), rs.getString(1), prisoner);
                convictionList.add(conviction);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return convictionList;
    }

    public void bookMeeting(Meeting meeting) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            stmt.executeUpdate("INSERT INTO meeting(PrisonerID,person,scheduledDate) values('" + ((Prisoner) meeting.getPrisoner()).getPrisonerId() + "','" + ((Person) meeting.getVisitor()).getCivicNumber() + "','" + meeting.getDate() + "')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Meeting> getMeetings(){
        ArrayList<Meeting> meetingList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select * from prisoners,person,meeting where prisoners.PrisonerId = meeting.PrisonerID and meeting.person = person.CivicNumber");
            while (rs.next()) {
                Civilian person = new Civilian(rs.getString(3), rs.getString(4), rs.getString(5));
                Prisoner prisoner = new Prisoner(null, null, null, rs.getInt(1), null);
                Meeting meeting = new Meeting(prisoner, person, rs.getDate(8));
                meetingList.add(meeting);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return meetingList;
    }

    public void addCrime(Crime crime){
        try{
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            stmt.executeUpdate("insert into crime(dateOfCrime,typeOfCrime,suspect,raportID) values('"+crime.getDateOfCrime()+"','"+crime.getTypeOfCrime()+"','"+((Person)crime.getSuspect()).getCivicNumber()+"','"+((CrimeRapport)crime.getRapport()).getRapportID()+"')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Crime> getCrime(){
        ArrayList<Crime> crimesList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select * from crime,crimeraport,wantedcriminal,person where wantedcriminal.CivicNumber = crime.suspect and crime.RaportID = crimeraport.RaportId and writter = person.civicnumber");
            while (rs.next()) {
                Person writter = new Civilian(rs.getString(13), rs.getString(14), rs.getString(15));
                CrimeRapport raport = new CrimeRapport(rs.getString(7), writter, rs.getInt(4));
                WantedCriminal wantedCriminal = new WantedCriminal(null, null, rs.getString(11), rs.getInt(10), rs.getInt(9), rs.getInt(12));
                Crime crime = new Crime(rs.getDate(1), rs.getString(2), wantedCriminal, raport);
                crimesList.add(crime);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return crimesList;
    }
    public void addCrimeRapport(CrimeRapport rapport) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            stmt.executeUpdate("INSERT INTO crimeraport(Writter,Text) values('" + ((Person) rapport.getWriter()).getCivicNumber() + "','" + rapport.getRapport() + "')");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<CrimeRapport> getCrimeRapport(){
        ArrayList<CrimeRapport> crimerapportList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            ResultSet rs = stmt.executeQuery("select * from person,crimeraport where person.civicnumber = writter");
            while (rs.next()) {
                Civilian person = new Civilian(rs.getString(1), rs.getString(2), rs.getString(3));
                CrimeRapport rapport = new CrimeRapport(rs.getString(5), person, rs.getInt(6));
                crimerapportList.add(rapport);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return crimerapportList;
    }
    public void changePrisonerFirstname(String firstname, int id){

    }
    public void changePrisonerLastname(String lastname, int id){

    }
    public void changePrisonerCivicNumber(String CN, int id){

    }
    public void changePrisonerReleaseDate(Date RD, int id){

    }
}
