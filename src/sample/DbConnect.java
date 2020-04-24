import java.sql.*;

public class DbConnect {
    public Connection connect(String username,String password) throws SQLException {
        Connection connect = DriverManager.getConnection("den1.mysql3.gear.host",username,password);
        return connect;
    }
}
