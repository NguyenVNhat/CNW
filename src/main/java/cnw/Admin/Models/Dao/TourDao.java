package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Tour;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class TourDao {
    private Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/cnw";
        String USER_NAME = "root";
        String PASSWORD = "";
        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        return conn;
    }
    public Tour getDetailTour(Integer Id) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "select * from TOUR where Id = '"+Id+"'";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next())
        {
             Integer IdTraveler = resultSet.getInt("IdTraveler");
             Integer IdInstructor = resultSet.getInt("IdTraveler");
             Integer IdAddress =  resultSet.getInt("IdAddress");
             Integer Price =  resultSet.getInt("Price");
             Integer ToTalTime = resultSet.getInt("ToTalTime");
             Date TimeStart = resultSet.getDate("TimeStart");
             Date TimeEnd = resultSet.getDate("TimeEnd");
             Boolean Status = resultSet.getBoolean("Status");
             return new Tour(Id, IdTraveler,IdInstructor,IdAddress,Price,ToTalTime,TimeStart,TimeEnd,Status);
        }
        return new Tour(null,null,null,null,null,null,null,null,null);
    }
    public ArrayList<Tour> getAllTour() throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "select * from TOUR ";
        ResultSet resultSet = statement.executeQuery(query);
        return null;
    }

}
