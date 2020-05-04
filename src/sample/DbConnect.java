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

    public ArrayList<Person> searchDb() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeQuery("use policemanagment");
        ResultSet rs = stmt.executeQuery("select * from person");
        ArrayList<Person> personList = new ArrayList<>();
        while(rs.next()){
            Civilian person = new Civilian(rs.getString(1),rs.getString(2),rs.getString(3));
            personList.add(person);
        }
        return personList;
    }
}
