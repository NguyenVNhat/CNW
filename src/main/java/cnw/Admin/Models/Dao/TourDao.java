package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Tour;

import java.sql.*;
import java.text.SimpleDateFormat;
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
        String query = "select tour.Id , instructor.Name,tour.Price,tour.TotalTime,tour.TimeStart,tour.Status" +
                " from TOUR " +
                "join Instructor ON Tour.IdInstructor = Instructor.Id\n" +
                " WHERE tour.Id = '"+Id+"'";
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next())
        {
             Integer ID = resultSet.getInt("Id");
             String Instructor = resultSet.getString("Name");
             Integer Price =  resultSet.getInt("Price");
             Integer ToTalTime = resultSet.getInt("ToTalTime");
             Date TimeStart = resultSet.getDate("TimeStart");
             Boolean Status = resultSet.getBoolean("Status");
             return new Tour(ID,Instructor,Price,ToTalTime,TimeStart,Status);
        }
        return new Tour(null,null,null,null,null,null);
    }
    public ArrayList<Tour> getAllTour() throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                "FROM TOUR " +
                "JOIN Instructor ON Tour.IdInstructor = Instructor.Id";

        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Tour> tours = new ArrayList<>();

        while (resultSet.next()) {
            Integer ID = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");


            Integer Price = resultSet.getInt("Price");
            Integer TotalTime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");

            Tour tour = new Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
            tours.add(tour);

        }

        return tours;
    }
    public ArrayList<Tour> getUpTour() throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                "FROM TOUR " +
                "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                "WHERE tour.TimeStart > NOW()";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Tour> tours = new ArrayList<>();

        while (resultSet.next()) {
            Integer ID = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");


            Integer Price = resultSet.getInt("Price");
            Integer TotalTime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");

            Tour tour = new Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
            tours.add(tour);

        }

        return tours;
    }
    public ArrayList<Tour> getDownTour() throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                "FROM TOUR " +
                "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                "WHERE DATE_ADD(tour.TimeStart, INTERVAL tour.TotalTime MINUTE) < NOW()";

        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Tour> tours = new ArrayList<>();

        while (resultSet.next()) {
            Integer ID = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");


            Integer Price = resultSet.getInt("Price");
            Integer TotalTime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");

            Tour tour = new Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
            tours.add(tour);

        }

        return tours;
    }

    public ArrayList<String > getListAddress(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT address.AddressName " +
                "FROM address " +
                "JOIN tour_address ON tour_address.IdAddress = address.Id \n" +
                "WHERE tour_address.IdTour = '"+IdTour+"'";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<String> address = new ArrayList<>();

        while (resultSet.next()) {
            String name = resultSet.getString("AddressName");
            address.add(name);
        }
        return address;
    }


    public Boolean addTour(Tour tour) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        Date defaultDateString = tour.getTimeStart();

        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = defaultDateFormat.format(defaultDateString);
        Integer status = 0;
        if(tour.getStatus())
        {
            status = 1;
        }
        String query = "INSERT INTO `tour`(`Id`,  `IdInstructor`, `Price`, `TotalTime`, `TimeStart`, `Status`) " +
                "VALUES ('"+tour.getId()+"','"+tour.getIdInstructor()+"'," +
                "'"+tour.getPrice()+"','"+tour.getToTalTime()+"','"+formattedDate+"'," +
                "'"+status+"') ";
        Integer result = statement.executeUpdate(query);
        if (result >0)
        {
            return  true;
        }
        else return  false;
    }
    public Boolean updateTour(Tour tour) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();

        Date defaultDateString = tour.getTimeStart();

        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = defaultDateFormat.format(defaultDateString);
        Integer status = 0;
        if(tour.getStatus())
        {
            status = 1;
        }

        Statement statement = connection.createStatement();
        String query = "UPDATE `tour` SET `IdInstructor`='"+tour.getIdInstructor()+"'," +
                "`Price`='"+tour.getPrice()+"',`TotalTime`='"+tour.getToTalTime()+"',`TimeStart`='"+formattedDate+"'," +
                "`Status`='"+status+"' WHERE `Id`='"+tour.getId()+"'";
        Integer result = statement.executeUpdate(query);
        if (result >0)
        {
            return  true;
        }
        else return  false;
    }
    public Boolean deleteTour(Integer Id) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM `tour` WHERE Id = '"+Id+"'";
        Integer result = statement.executeUpdate(query);
        if (result >0)
        {
            return  true;
        }
        else return  false;
    }

}
