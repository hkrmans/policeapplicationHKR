// Not fully functioning.
import java.sql.*;

public class DbConnection {
    public Connection connect(String username, String password) {
        try {
            Connection connect = DriverManager.getConnection("den1.mysql3.gear.host", username, password);
            return connect;
        } catch (SQLException E) {
        }
        return null;
    }
    public void search(Connection connect,String statement) throws SQLException {
        Statement stmt = connect.createStatement();
    }

}
