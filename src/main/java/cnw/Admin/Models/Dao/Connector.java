package cnw.Admin.Models.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    Connection connection =null;

    public Connection connectDB() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            Class.forName("com.mysql.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost:3306/cnw";
            String USER_NAME = "root";
            String PASSWORD = "";
            connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        }
        return connection;
    }
}
