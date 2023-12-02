package cnw.Admin.Models.Dao;

import java.sql.*;

public class AdminDao {
    private Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/cnw";
        String USER_NAME = "root";
        String PASSWORD = "";
        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        return conn;
    }
    public Boolean isExitAdmin(String adminname,String password) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement sttm = connection.createStatement();
        String query = "select * from ADMIN where Adminname = '"+adminname+"' and Password = '"+password+"'";
        ResultSet resultSet = sttm.executeQuery(query);
        if(resultSet.next())
        {
            return true;
        }
        return false;
    }

}
