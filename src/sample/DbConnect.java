package sample;

import javafx.scene.control.Alert;
import sample.Models.*;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class DbConnect<T> {
    private static DbConnect single_instance = null;
    private static Connection connection;
    private static Security security = new Security();

    public static DbConnect getInstance(String password) {
        if (single_instance == null) {
            single_instance = new DbConnect(password);
        }
        return single_instance;
    }

    private DbConnect(String password) {
        password = security.decrypter(password);
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host", "policemanagment", password);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to connect to the database");
            alert.showAndWait();
        }
    }

    public <T> void updateInfo(T info) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            if (info instanceof Person) {
                stmt.executeUpdate("update person set firstName = " + '"' + ((Person) info).getFirstName() + '"' + ',' + " lastName = " + '"' + ((Person) info).getLastName() + '"' + " where civicnumber = " + "'" + ((Person) info).getCivicNumber() + "'");
                if (info instanceof WantedCriminal) {
                    stmt.executeUpdate("update wantedcriminal set bounty = " + ((WantedCriminal) info).getBounty() + ", WantedRanking = " + ((WantedCriminal) info).getRanking() + " where WantedID = " + ((WantedCriminal) info).getWantedId());
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to update information in the database");
            alert.showAndWait();
        }
    }

    public <T> void deleteInformation(T info) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            if (info instanceof Meeting) {
                stmt.executeUpdate("delete from meeting where meeting.meetingID = " + ((Meeting) info).getMeetingID());
            } else if (info instanceof Conviction) {
                stmt.executeUpdate("delete from convictions where convictions.convictionID = " + ((Conviction) info).getConvictionID());
            } else if (info instanceof Account) {
                stmt.executeUpdate("delete from account where account.username = " + '"' + ((Account) info).getUsername() + '"');
            } else if (info instanceof Police) {
                stmt.executeUpdate("delete from police where police.BadgeId = " + '"' + ((Police) info).getBadgeId() + '"');
            } else if (info instanceof WantedCriminal) {
                stmt.executeUpdate("delete from wantedcriminal where wantedcriminal.WantedID = " + ((WantedCriminal) info).getWantedId());
            } else if (info instanceof CrimeReport) {
                stmt.executeUpdate("delete from crime where crime.raportID = " + ((CrimeReport) info).getRapportID());
                stmt.executeUpdate("delete from crimeraport where crimeraport.RaportId = " + ((CrimeReport) info).getRapportID());
            } else if (info instanceof Crime) {
                stmt.executeUpdate("delete from crime where crime.crimeID = " + ((Crime) info).getCrimeID());
            } else if (info instanceof Prisoner) {
                stmt.executeUpdate("delete from meeting where meeting.PrisonerID = " + ((Prisoner) info).getPrisonerId());
                stmt.executeUpdate("delete from convictions where convictions.PrisonerID = " + ((Prisoner) info).getPrisonerId());
                stmt.executeUpdate("delete from prisoners where prisoners.PrisonerId = " + ((Prisoner) info).getPrisonerId());
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to update information in the database");
            alert.showAndWait();
        }
    }

    public <T> void addInformation(T info) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            if (info instanceof Person) {
                stmt.executeUpdate("INSERT INTO person(firstName,lastName,civicnumber) values('" + ((Person) info).getFirstName() + "','" + ((Person) info).getLastName() + "','" + ((Person) info).getCivicNumber() + "')");
                if (info instanceof Police) {
                    stmt.executeUpdate("INSERT INTO police(CivicNumber,BadgeId) values('" + ((Police) info).getCivicNumber() + "','" + ((Police) info).getBadgeId() + "')");
                } else if (info instanceof WantedCriminal) {
                    stmt.executeUpdate("INSERT INTO WantedCriminal(Bounty,WantedRanking,CivicNumber) values('" + ((WantedCriminal) info).getBounty() + "','" + ((WantedCriminal) info).getRanking() + "','" + ((WantedCriminal) info).getCivicNumber() + "')");
                } else if (info instanceof Prisoner) {
                    stmt.executeUpdate("INSERT INTO prisoners(CivicNumber) values('" + ((Prisoner) info).getCivicNumber() + "')");
                }
            } else if (info instanceof Account) {
                stmt.executeUpdate("INSERT INTO account(username,CivicNumber,password,email) values('" + ((Account) info).getUsername() + "','" + (((Account) info).getOwner().getCivicNumber() + "','" + security.hashPassword(((Account) info).getPassword()) + "','" + ((Account) info).getEmail() + "')"));
            } else if (info instanceof Conviction) {
                stmt.executeUpdate("INSERT INTO convictions(sentence,convictionDate,realeseDate,PrisonerID) values('" + ((Conviction) info).getSentence() + "','" + ((Conviction) info).getConviction() + "','" + ((Conviction) info).getRelease() + "','" + (((Conviction) info).getPrisoner().getPrisonerId()) + "')");
            } else if (info instanceof Meeting) {
                stmt.executeUpdate("INSERT INTO meeting(PrisonerID,person,scheduledDate) values('" + ((Meeting) info).getPrisoner().getPrisonerId() + "','" + ((Meeting) info).getVisitor().getCivicNumber() + "','" + ((Meeting) info).getDate() + "')");
            } else if (info instanceof Crime) {
                stmt.executeUpdate("insert into crime(dateOfCrime,typeOfCrime,suspect,raportID) values('" + ((Crime) info).getDateOfCrime() + "','" + ((Crime) info).getTypeOfCrime() + "','" + ((Crime) info).getSuspect().getCivicNumber() + "','" + ((Crime) info).getRapport().getRapportID() + "')");
            } else if (info instanceof CrimeReport) {
                stmt.executeUpdate("INSERT INTO crimeraport(Writter,Text) values('" + ((CrimeReport) info).getWriter().getCivicNumber() + "','" + ((CrimeReport) info).getRapport() + "')");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to update information in the database");
            alert.showAndWait();
        }
    }

    public void getInfo(ArrayList<T> list) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("use policemanagment");
            if (list.get(0) instanceof Police) {
                ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,BadgeId from police,person where person.civicnumber = police.CivicNumber");
                while (rs.next()) {
                    Police police = new Police(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                    list.add((T) police);
                }
            } else if (list.get(0) instanceof Civilian) {
                ResultSet rs = stmt.executeQuery("select * from person");
                while (rs.next()) {
                    Civilian person = new Civilian(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add((T) person);
                }
            } else if (list.get(0) instanceof WantedCriminal) {
                ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,WantedID,WantedRanking,Bounty from person,wantedcriminal where person.civicnumber = wantedcriminal.CivicNumber");
                while (rs.next()) {
                    WantedCriminal criminal = new WantedCriminal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), rs.getInt(6), rs.getInt(4));
                    list.add((T) criminal);
                }
            } else if (list.get(0) instanceof Prisoner) {
                ResultSet rs = stmt.executeQuery("select firstName,lastName,person.civicnumber,PrisonerId from person,prisoners where person.civicnumber = prisoners.CivicNumber");
                while (rs.next()) {
                    Prisoner prisoner = new Prisoner(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    list.add((T) prisoner);
                }
            } else if (list.get(0) instanceof Account) {
                ResultSet rs = stmt.executeQuery("select * from person, account where person.civicnumber = account.CivicNumber");
                while (rs.next()) {
                    Person person = new Civilian(rs.getString(1), rs.getString(2), rs.getString(3));
                    Account account = new Account(person, rs.getString(4), rs.getString(6), rs.getString(7));
                    list.add((T) account);
                }
            } else if (list.get(0) instanceof Conviction) {
                ResultSet rs = stmt.executeQuery("select * from convictions, prisoners where convictions.PrisonerID = prisoners.PrisonerId");
                while (rs.next()) {
                    Prisoner prisoner = new Prisoner(null, null, rs.getString(7), rs.getInt(6));
                    Conviction conviction = new Conviction(rs.getDate(4), rs.getDate(3), rs.getString(1), prisoner, rs.getInt(2));
                    list.add((T) conviction);
                }
            } else if (list.get(0) instanceof Meeting) {
                ResultSet rs = stmt.executeQuery("select * from prisoners,person,meeting where prisoners.PrisonerId = meeting.PrisonerID and meeting.person = person.CivicNumber");
                while (rs.next()) {
                    Civilian person = new Civilian(rs.getString(3), rs.getString(4), rs.getString(5));
                    Prisoner prisoner = new Prisoner(null, null, null, rs.getInt(1));
                    Meeting meeting = new Meeting(prisoner, person, rs.getDate(8), rs.getInt(9));
                    list.add((T) meeting);
                }
            } else if (list.get(0) instanceof Crime) {
                ResultSet rs = stmt.executeQuery("select * from crime,crimeraport,wantedcriminal,person where wantedcriminal.CivicNumber = crime.suspect and crime.RaportID = crimeraport.RaportId and writter = person.civicnumber");
                while (rs.next()) {
                    Person writter = new Civilian(rs.getString(13), rs.getString(14), rs.getString(15));
                    CrimeReport raport = new CrimeReport(rs.getString(7), writter, rs.getInt(4));
                    WantedCriminal wantedCriminal = new WantedCriminal(null, null, rs.getString(11), rs.getInt(10), rs.getInt(9), rs.getInt(12));
                    Crime crime = new Crime(rs.getDate(1), rs.getString(2), wantedCriminal, raport, rs.getInt(5));
                    list.add((T) crime);
                }
            } else if (list.get(0) instanceof CrimeReport) {
                ResultSet rs = stmt.executeQuery("select * from person,crimeraport where person.civicnumber = writter");
                while (rs.next()) {
                    Civilian person = new Civilian(rs.getString(1), rs.getString(2), rs.getString(3));
                    CrimeReport rapport = new CrimeReport(rs.getString(5), person, rs.getInt(6));
                    list.add((T) rapport);
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to update information in the database");
            alert.showAndWait();
        }
        list.remove(0);
    }
}
