package sample;

import java.sql.*;
import java.util.*;

class DbConnect{
    private static DbConnect single_instance = null;
    private static Connection connection;

    DbConnect(String username, String password) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host",username,password);
    }

    public static Connection getInstance(){
        return connection;
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
}
