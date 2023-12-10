package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Instructor;

import java.sql.*;
import java.util.ArrayList;

public class IntructorDao {

    private Connector connector = new Connector();
    public Integer getInstructor_count() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) as count FROM instructor";
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }
    public Instructor getDetail(Integer Id) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT *  FROM instructor where Id = '"+Id+"' ";

        ResultSet resultSet = statement.executeQuery(query);
        Instructor ins = new Instructor(null,null,null,null,null);

        while (resultSet.next()) {
            Integer id = resultSet.getInt("Id");
            String name = resultSet.getString("Name");
            Integer Age= resultSet.getInt("Age");
            String Email =resultSet.getString("Email");
            String Phone =resultSet.getString("Phone");
            ins.setId(id);
            ins.setAge(Age);
            ins.setEmail(Email);
            ins.setName(name);
            ins.setPhone(Phone);
        }
        return ins;
    }
    public ArrayList<Instructor> getAllIntructor() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT * " +
                "FROM instructor " +
                "";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Instructor> ins = new ArrayList<>();

        while (resultSet.next()) {
            Integer id = resultSet.getInt("Id");
            String name = resultSet.getString("Name");
            Integer Age= resultSet.getInt("Age");
            String Email =resultSet.getString("Email");
            String Phone =resultSet.getString("Phone");
            ins.add(new Instructor(id,name,Age,Email,Phone));
        }
        return ins;
    }
    public ArrayList<Instructor> getListIntructor(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
             Integer Age= resultSet.getInt("Age");
             String Email =resultSet.getString("Email");
             String Phone =resultSet.getString("Phone");
            ins.add(new Instructor(id,name,Age,Email,Phone));
        }
        return ins;
    }
    public void Add(Instructor instructor) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO `instructor`(`Id`, `Name`, `Age`, `Email`, `Phone`) VALUES ('"+instructor.getId()+"','"+instructor.getName()+"','"+instructor.getAge()+"','"+instructor.getEmail()+"','"+instructor.getPhone()+"')";
        int res = statement.executeUpdate(query);
    }
    public void Update(Integer Id , String name, Integer age,String email,String phone) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "UPDATE `instructor` SET `Name`='"+name+"',`Age`='"+age+"',`Email`='"+email+"',`Phone`='"+phone+"' WHERE `Id`='"+Id+"'";
        int res = statement.executeUpdate(query);
    }
}
