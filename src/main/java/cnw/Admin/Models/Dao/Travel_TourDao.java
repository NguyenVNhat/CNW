package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Bean.Traveler;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Travel_TourDao {
    private Connection connectDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/cnw";
        String USER_NAME = "root";
        String PASSWORD = "";
        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        return conn;
    }
    public ArrayList<Travel_Tour> getALlTravelTour() throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT\n" +
                "    tour.Id,\n" +
                "    instructor.Name,\n" +
                "    tour.Price,\n" +
                "    tour.TotalTime,\n" +
                "    tour.TimeStart,\n" +
                "    tour.Status\n" +
                "FROM\n" +
                "    traveler_tour\n" +
                "JOIN\n" +
                "    tour ON tour.Id = traveler_tour.IdTour\n" +
                "JOIN\n" +
                "    instructor ON tour.IdInstructor = instructor.Id\n" +
                "GROUP BY tour.Id;\n";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Travel_Tour> tours = new ArrayList<>();
        while (resultSet.next())
        {
            Integer Id = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");
            Integer price = resultSet.getInt("Price");
            Integer totaltime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");
            Travel_Tour tour = new Travel_Tour(Id,Instructor,price,totaltime,TimeStart,Status);
            tours.add(tour);
        }
        for (Travel_Tour travel_tour: tours
             ) {
            travel_tour.setListTraveler(getListTraveler(travel_tour.getIdTour()));
            travel_tour.setListAddress(getListAddress(travel_tour.getIdTour()));
        }
        return tours;

    }
    public ArrayList<Traveler > getListTraveler(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT * " +
                "FROM traveler " +
                "JOIN traveler_tour ON traveler_tour.IdTraveler = traveler.Id \n" +
                "WHERE traveler_tour.IdTour = '"+IdTour+"'";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Traveler> travelers = new ArrayList<>();

        while (resultSet.next()) {
            Integer Id = resultSet.getInt("Id");
            String Name = resultSet.getString("Name");
            String Address = resultSet.getString("Address");
            Date DayBorn = resultSet.getDate("DayBorn");

            String Email = resultSet.getString("Email");
            String Phone = resultSet.getString("Phone");

            travelers.add(new Traveler(Id,Name,Address,DayBorn,Email,Phone));

        }
        return travelers;
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
}
