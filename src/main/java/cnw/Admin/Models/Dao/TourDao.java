package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Tour;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TourDao {

    private Connector connector = new Connector();
    public Integer getTour_count() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) as count FROM tour";
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }
    public ArrayList<Integer> getTourWeek() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT \n" +
                "    DAYOFWEEK(timeStart) AS DayOfWeek,\n" +
                "    COUNT(*) AS NumberOfTours\n" +
                "FROM tour\n" +
                "WHERE timeStart >= CURDATE() - INTERVAL WEEKDAY(CURDATE()) DAY\n" +
                "    AND timeStart < CURDATE() - INTERVAL WEEKDAY(CURDATE()) - 6 DAY\n" +
                "GROUP BY DAYOFWEEK(timeStart);\n";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Integer> tours = new ArrayList<>();
        for (int i=0;i<7;i++)
        {
            tours.add(0);
        }
        while (resultSet.next())
        {
            String day = resultSet.getString("DayOfWeek");
            Integer revenue = resultSet.getInt("NumberOfTours");
            if(day.equals("2"))
            {
                tours.set(0,revenue);
            }
            else if(day.equals("3"))
            {
                tours.set(1,revenue);
            }
            else if(day.equals("4"))
            {
                tours.set(2,revenue);
            }
            else if(day.equals("5"))
            {
                tours.set(3,revenue);
            }
            else if(day.equals("6"))
            {
                tours.set(4,revenue);
            }
            else if(day.equals("7"))
            {
                tours.set(5,revenue);
            }
            else if(day.equals("8"))
            {
                tours.set(6,revenue);
            }
        }
        return tours;
    }
    public ArrayList<Tour> findTourDay(String textsearch) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = null;
        ArrayList<Tour> tours = new ArrayList<>();
        if(textsearch.equals("1"))
        {
            query = "select tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  from tour " +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                    " where tour.TimeStart = NOW()";
        } else if (textsearch.equals("2")) {
            query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  FROM tour " +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                    "WHERE DATE(tour.TimeStart) = CURDATE() - INTERVAL 1 DAY";
        }else if (textsearch.equals("3")) {
            query = "SELECT tour.Id, Instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  \n" +
                    "FROM tour \n" +
                    "JOIN Instructor ON tour.IdInstructor = Instructor.Id \n" +
                    "WHERE tour.TimeStart >= CURDATE() - INTERVAL 7 DAY \n" +
                    "AND tour.TimeStart <= CURDATE()";
        }else if (textsearch.equals("4")) {
            query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  FROM tour\n" +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                    "WHERE MONTH(tour.TimeStart) = MONTH(CURDATE()) AND YEAR(tour.TimeStart) = YEAR(CURDATE())";
        }else if (textsearch.equals("5")) {
            query="SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status  FROM tour\n" +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
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

            Tour tour = new Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
            tours.add(tour);

        }
        return tours;
    }
    public ArrayList<Tour> findTour(String textsearch,String pageId) throws SQLException, ClassNotFoundException {
        ArrayList<Tour> tours = new ArrayList<>();
        Connection connection = connector.connectDB();
            Statement statement = connection.createStatement();
            String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                    "FROM TOUR " +
                    "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
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

                Tour tour = new Tour(ID, Instructor, Price, TotalTime, TimeStart, Status);
                tours.add(tour);

            }
        return tours;
    }
    public Tour getDetailTour(Integer Id) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
        return new Tour(null,null,null,null,null);
    }
    public ArrayList<Tour> getAllTour() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
        Connection connection = connector.connectDB();
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
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                "FROM TOUR " +
                "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                "WHERE DATE_ADD(tour.TimeStart, INTERVAL tour.TotalTime DAY) < NOW()";


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
    public ArrayList<Tour> getCurrentTour() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT tour.Id, instructor.Name, tour.Price, tour.TotalTime, tour.TimeStart, tour.Status " +
                "FROM TOUR " +
                "JOIN Instructor ON Tour.IdInstructor = Instructor.Id " +
                "WHERE   tour.TimeStart <= NOW()" +
        "AND DATE_ADD(tour.TimeStart, INTERVAL tour.TotalTime DAY) > NOW()";

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


    public Boolean addTour(Tour tour) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
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
                "VALUES ('"+tour.getId()+"','"+tour.getIdIntructor()+"'," +
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
        Connection connection = connector.connectDB();

        Date defaultDateString = tour.getTimeStart();

        SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = defaultDateFormat.format(defaultDateString);
        Integer status = 0;
        if(tour.getStatus())
        {
            status = 1;
        }

        Statement statement = connection.createStatement();
        String query = "UPDATE `tour` SET `IdInstructor`='"+tour.getIdIntructor()+"'," +
                "`Price`='"+tour.getPrice()+"',`TotalTime`='"+tour.getToTalTime()+"',`TimeStart`='"+formattedDate+"'," +
                "`Status`='"+status+"' WHERE `Id`='"+tour.getId()+"'";
        Integer result = statement.executeUpdate(query);
        if (result >0)
        {
            return  true;
        }
        else return  false;
    }
    public void deleteTour(Integer Id) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "DELETE FROM `tour` WHERE Id = '"+Id+"'";
        Integer result = statement.executeUpdate(query);

    }

}
