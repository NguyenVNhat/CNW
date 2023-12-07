package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Instructor;

import java.sql.*;
import java.util.ArrayList;

public class IntructorDao {
    private Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/cnw";
        String USER_NAME = "root";
        String PASSWORD = "";
        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        return conn;
    }
    public ArrayList<Instructor> getAllIntructor() throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT * " +
                "FROM instructor " +
                "JOIN tour ON tour.IdInstructor = instructor.Id \n" +
                "";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Instructor> ins = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("Id");
            String name = resultSet.getString("Name");
            String Age= resultSet.getString("Age");
            String Email =resultSet.getString("Email");
            String Phone =resultSet.getString("Phone");
            ins.add(new Instructor(id,name,Age,Email,Phone));
        }
        return ins;
    }
    public ArrayList<Instructor> getListIntructor(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT *" +
                "FROM instructor " +
                "JOIN tour ON tour.IdInstructor = instructor.Id \n" +
                "WHERE tour.Id = '"+IdTour+"'";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Instructor> ins = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("Id");
            String name = resultSet.getString("Name");
             String Age= resultSet.getString("Age");
             String Email =resultSet.getString("Email");
             String Phone =resultSet.getString("Phone");
            ins.add(new Instructor(id,name,Age,Email,Phone));
        }
        return ins;
    }
}
