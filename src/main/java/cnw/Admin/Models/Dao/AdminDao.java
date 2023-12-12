package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Admin;
import cnw.utils.Connector;

import java.sql.*;
import java.util.Optional;

public class AdminDao {
    private Connector connector = new Connector();
    public Boolean isExitAdmin(String adminname,String password) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement sttm = connection.createStatement();
        String query = "select * from ADMIN where Username = '"+adminname+"' and Password = '"+password+"'";
        ResultSet resultSet = sttm.executeQuery(query);
        if(resultSet.next())
        {
            return true;
        }
        return false;
    }
    public Optional<Admin> getAdminByUsername(String username) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement sttm = connection.createStatement();
        String query = "select * from ADMIN where Username = '"+username+"'";
        ResultSet resultSet = sttm.executeQuery(query);
        if(resultSet.next())
        {
            Admin admin = new Admin();
            admin.setId(resultSet.getInt("Id"));
            admin.setAdminname(resultSet.getString("Username"));
            admin.setPassword(resultSet.getString("Password"));
            return Optional.of(admin);
        }
        return Optional.empty();
    }

}
