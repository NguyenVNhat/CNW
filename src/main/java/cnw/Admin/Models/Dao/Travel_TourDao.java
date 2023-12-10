package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.AdressDiagram;
import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Bean.Traveler;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Travel_TourDao {
    private Connector connector = new Connector();
    public Integer getTraveler_Tour_count() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = " SELECT COUNT(*) AS RowCount FROM (\n" +
                "    SELECT COUNT(*) AS count \n" +
                "    FROM traveler_tour \n" +
                "    GROUP BY traveler_tour.IdTour\n" +
                ") AS subquery;\n ";
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt("RowCount");
        }
        return count;
    }
    public Integer revenueAllOfMonth() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT SUM(tour.Price) AS TotalMonthRevenue " +
                "FROM traveler_tour " +
                "JOIN tour ON tour.Id = traveler_tour.IdTour " +
                "WHERE YEAR(tour.TimeStart) = YEAR(NOW()) " +
                "AND MONTH(tour.TimeStart) = MONTH(NOW())";

        ResultSet resultSet = statement.executeQuery(query);
        Integer revenue = 0;
        while (resultSet.next())
        {
            revenue =   resultSet.getInt("TotalMonthRevenue");
        }
        return revenue;
    }
    public Integer revenueAllOfWeek() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT SUM(tour.Price) AS TotalWeekRevenue " +
                "FROM traveler_tour " +
                "JOIN tour ON tour.Id = traveler_tour.IdTour " +
                "WHERE YEARWEEK(tour.TimeStart, 1) = YEARWEEK(NOW(), 1)";
        ResultSet resultSet = statement.executeQuery(query);
        Integer revenue = 0;
        while (resultSet.next())
        {
            revenue =   resultSet.getInt("TotalWeekRevenue");
        }
        return revenue;
    }
    public ArrayList<Integer> revenueWeek() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT DAYNAME(tour.TimeStart) AS DayOfWeek, \n" +
                "                      SUM(tour.Price) AS TotalRevenue \n" +
                "               FROM traveler_tour \n" +
                "               JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                "               WHERE YEARWEEK(tour.TimeStart, 1) = YEARWEEK(NOW(), 1) \n" +
                "               GROUP BY DAYOFWEEK(tour.TimeStart) \n" +
                "               ORDER BY DAYOFWEEK(tour.TimeStart)\n";

        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Integer> revenues = new ArrayList<>();
        for (int i=0;i<7;i++)
        {
            revenues.add(0);
        }
        while (resultSet.next())
        {
            String day = resultSet.getString("DayOfWeek");
            Integer revenue = resultSet.getInt("TotalRevenue");
            if(day.equals("Monday"))
            {
                revenues.set(0,revenue);
            }
            else if(day.equals("Tuesday"))
            {
                revenues.set(1,revenue);
            }
            else if(day.equals("Wednesday"))
            {
                revenues.set(2,revenue);
            }
            else if(day.equals("Thursday"))
            {
                revenues.set(3,revenue);
            }
            else if(day.equals("Friday"))
            {
                revenues.set(4,revenue);
            }
            else if(day.equals("Saturday"))
            {
                revenues.set(5,revenue);
            }
            else if(day.equals("Sunday"))
            {
                revenues.set(6,revenue);
            }
        }
        return revenues;

    }

    public ArrayList<Integer> revenueWeek(Date date) throws SQLException, ClassNotFoundException {

        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = defaultDateFormat.format(date);
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT DAYNAME(tour.TimeStart) AS DayOfWeek, \n" +
                "                      SUM(tour.Price) AS TotalRevenue \n" +
                "               FROM traveler_tour \n" +
                "               JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                "               WHERE YEARWEEK(tour.TimeStart, 1) = YEARWEEK('"+formattedDate+"', 1) \n" +
                "               GROUP BY DAYOFWEEK(tour.TimeStart) \n" +
                "               ORDER BY DAYOFWEEK(tour.TimeStart)\n";

        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Integer> revenues = new ArrayList<>();
        for (int i=0;i<7;i++)
        {
            revenues.add(0);
        }
        while (resultSet.next())
        {
            String day = resultSet.getString("DayOfWeek");
            Integer revenue = resultSet.getInt("TotalRevenue");
            if(day.equals("Monday"))
            {
                revenues.set(0,revenue);
            }
            else if(day.equals("Tuesday"))
            {
                revenues.set(1,revenue);
            }
            else if(day.equals("Wednesday"))
            {
                revenues.set(2,revenue);
            }
            else if(day.equals("Thursday"))
            {
                revenues.set(3,revenue);
            }
            else if(day.equals("Friday"))
            {
                revenues.set(4,revenue);
            }
            else if(day.equals("Saturday"))
            {
                revenues.set(5,revenue);
            }
            else if(day.equals("Sunday"))
            {
                revenues.set(6,revenue);
            }
        }
        return revenues;

    }
    public ArrayList<Travel_Tour> getALlTravelTour() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
    public ArrayList<Travel_Tour> getUpTravelTour() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
                "WHERE tour.TimeStart > NOW()\n" +
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
    public ArrayList<Travel_Tour> getDownTravelTour() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
                "WHERE DATE_ADD(tour.TimeStart, INTERVAL tour.TotalTime DAY) < NOW() \n"+
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
    public ArrayList<Travel_Tour> getCurrentTravelTour() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
                "WHERE   tour.TimeStart <= NOW()" +
                "AND DATE_ADD(tour.TimeStart, INTERVAL tour.TotalTime DAY) > NOW() \n"+
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
    public Travel_Tour getDetailTour(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
                "WHERE tour.Id = '"+IdTour+"'" +
                "GROUP BY tour.Id;\n";
        ResultSet resultSet = statement.executeQuery(query);
        Travel_Tour tour = null;
        while (resultSet.next())
        {
            Integer Id = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");
            Integer price = resultSet.getInt("Price");
            Integer totaltime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");
            tour = new Travel_Tour(Id,Instructor,price,totaltime,TimeStart,Status);
            tour.setListTraveler(getListTraveler(tour.getIdTour()));
            tour.setListAddress(getListAddress(tour.getIdTour()));
        }

        return tour;

    }

    public ArrayList<Traveler > getListTraveler(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
    public ArrayList<Traveler > getListTraveler() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT * " +
                "FROM traveler ";
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
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT address.Address " +
                "FROM address " +
                "JOIN tour_address ON tour_address.IdAddress = address.Id \n" +
                "WHERE tour_address.IdTour = '"+IdTour+"'";


        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<String> address = new ArrayList<>();

        while (resultSet.next()) {
            String name = resultSet.getString("Address");
            address.add(name);
        }
        return address;
    }
    public void Delete(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "DELETE from traveler_tour where IdTour = '"+IdTour+"'";
        int res = statement.executeUpdate(query);

    }
    public void DeleteTravelOnTour(Integer IdTour,Integer IdTraveler) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "DELETE from traveler_tour where IdTour = '"+IdTour+"' and IdTraveler ='"+IdTraveler+"'";
        int res = statement.executeUpdate(query);
    }
    public Boolean AddTravelOnTour(Integer IdTour,Integer IdTraveler) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String queryCheck = "Select * from traveler_tour WHERE IdTour = '"+IdTour+"' and IdTraveler='"+IdTraveler+"'";
        ResultSet resultSet = statement.executeQuery(queryCheck);
        if(resultSet.next())
        {
            return false;
        }
        else {
            String query = "INSERT INTO `traveler_tour`(`IdTour`, `IdTraveler`) VALUES ('" + IdTour + "','" + IdTraveler + "')";
            int res = statement.executeUpdate(query);
            if (res < 0) return false;
        }
        return true;


    }
    public ArrayList<Travel_Tour> findTourDay(String textsearch) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = null;
        ArrayList<Travel_Tour> tours = new ArrayList<>();
        if(textsearch.equals("1"))
        {
            query = "select tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  from traveler_tour \n" +
                    "JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                    " where tour.TimeStart = NOW()";
        } else if (textsearch.equals("2")) {
            query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  FROM traveler_tour \n" +
                    "JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                    "WHERE DATE(tour.TimeStart) = CURDATE() - INTERVAL 1 DAY";
        }else if (textsearch.equals("3")) {
            query = "SELECT tour.Id, Instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  \n" +
                    "FROM traveler_tour \n" +
                    "JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                    "JOIN Instructor ON tour.IdInstructor = Instructor.Id \n" +
                    "WHERE tour.TimeStart >= CURDATE() - INTERVAL 7 DAY \n" +
                    "AND tour.TimeStart <= CURDATE()";
        }else if (textsearch.equals("4")) {
            query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  FROM traveler_tour\n" +
                    "JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id \n" +
                    "WHERE MONTH(tour.TimeStart) = MONTH(CURDATE()) AND YEAR(tour.TimeStart) = YEAR(CURDATE())";
        }else if (textsearch.equals("5")) {
            query="SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  FROM traveler_tour\n"+
                    "JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id \n" +
                    "WHERE \n" +
                    "    (YEAR(tour.TimeStart) = YEAR(CURDATE()) AND MONTH(tour.TimeStart) = MONTH(CURDATE()) - 1)\n" +
                    "    OR\n" +
                    "    (YEAR(tour.TimeStart) = YEAR(CURDATE()) - 1 AND MONTH(tour.TimeStart) = 12)";
        }
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer ID = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");
            Integer Price = resultSet.getInt("Price");
            Integer TotalTime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");

            Travel_Tour tour = new Travel_Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
            tours.add(tour);

        }
        for (Travel_Tour travel_tour: tours
        ) {
            travel_tour.setListTraveler(getListTraveler(travel_tour.getIdTour()));
            travel_tour.setListAddress(getListAddress(travel_tour.getIdTour()));
        }
        return tours;
    }

    public ArrayList<Travel_Tour> findTravelTour(String textsearch,String pageId) throws SQLException, ClassNotFoundException {
        ArrayList<Travel_Tour> tours = new ArrayList<>();
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                "FROM traveler_tour " +
                "JOIN tour ON tour.Id = traveler_tour.IdTour \n" +
                "JOIN Instructor ON Tour.IdInstructor = Instructor.Id \n" +
                "WHERE tour.Id LIKE '%" + textsearch + "%' " +
                "OR instructor.Name LIKE '%" + textsearch + "%' " +
                "OR tour.Price LIKE '%" + textsearch + "%' " +
                "OR tour.TotalTime LIKE '%" + textsearch + "%' " +
                "OR tour.TimeStart like '%"+textsearch+"%'";

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer ID = resultSet.getInt("Id");
            String Instructor = resultSet.getString("Name");
            Integer Price = resultSet.getInt("Price");
            Integer TotalTime = resultSet.getInt("TotalTime");
            Date TimeStart = resultSet.getDate("TimeStart");
            Boolean Status = resultSet.getBoolean("Status");

            Travel_Tour tour = new Travel_Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
            tours.add(tour);

        }
        for (Travel_Tour travel_tour: tours
        ) {
            travel_tour.setListTraveler(getListTraveler(travel_tour.getIdTour()));
            travel_tour.setListAddress(getListAddress(travel_tour.getIdTour()));
        }
        return tours;
    }
}
