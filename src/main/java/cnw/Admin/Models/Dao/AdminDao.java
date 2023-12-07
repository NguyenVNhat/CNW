package cnw.Admin.Models.Dao;

import cnw.Utils.Connector;

import java.sql.*;

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

}
