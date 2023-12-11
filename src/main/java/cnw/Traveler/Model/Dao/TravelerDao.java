package cnw.Traveler.Model.Dao;

import cnw.Traveler.Model.Bean.Traveler;
import cnw.Traveler.Model.dto.ChangePasswordDto;
import cnw.Traveler.Model.dto.RegisterTravelerDto;
import cnw.Traveler.Model.dto.UpdateTravelerProfileDto;
import cnw.utils.Connector;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TravelerDao {
    Logger logger = Logger.getLogger(TravelerDao.class.getName());
    private final Connector connector = new Connector();
    public Optional<Traveler> getTravelerById(Integer id) {
        try {
            Connection connection = connector.connectDB();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from traveler where Id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return Optional.of(new Traveler(resultSet.getInt("Id"),resultSet.getString("Username"),resultSet.getString("Password"),resultSet.getString("Name"),resultSet.getString("Address"),resultSet.getDate("DayBorn"),resultSet.getString("Email"),resultSet.getString("Phone"), resultSet.getString("Avatar")));
            }
            return Optional.empty();
        }
        catch (SQLException e){
            logger.warning(e.getMessage());
            return Optional.empty();
        }
        catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public Optional<Traveler> getTravelerByUsername(String username) {
        try {
            Connection connection = connector.connectDB();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from traveler where Username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return Optional.of(new Traveler(resultSet.getInt("Id"),resultSet.getString("Username"),resultSet.getString("Password"),resultSet.getString("Name"),resultSet.getString("Address"),resultSet.getDate("DayBorn"),resultSet.getString("Email"),resultSet.getString("Phone"), resultSet.getString("Avatar")));
            }
        }
        catch (SQLException e){
            logger.warning(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public void registerTraveler(RegisterTravelerDto traveler) {
        try {
            Connection connection = connector.connectDB();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into traveler(Username, Password, Name, Email) values(?,?,?,?)");
            preparedStatement.setString(1, traveler.getUsername());
            preparedStatement.setString(2, traveler.getPassword());
            preparedStatement.setString(3, traveler.getName());
            preparedStatement.setString(4, traveler.getEmail());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            logger.warning(e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateTraveler(UpdateTravelerProfileDto traveler) {
        try {
            Connection connection = connector.connectDB();
            PreparedStatement preparedStatement = connection.prepareStatement("update traveler set Name = ?, Address = ?, DayBorn = ?, Email = ?, Phone = ?, Avatar = ? where Id = ?");
            preparedStatement.setString(1, traveler.getName());
            preparedStatement.setString(2, traveler.getAddress());
            preparedStatement.setDate(3, traveler.getDayBorn());
            preparedStatement.setString(4, traveler.getEmail());
            preparedStatement.setString(5, traveler.getPhone());
            preparedStatement.setString(6, traveler.getFileName());
            preparedStatement.setInt(7, traveler.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.warning(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//    public List<Traveler> getAllTravelerInTour(Integer idTour) throws SQLException, ClassNotFoundException {
//        Connection connection = connector.connectDB();
//        Statement sttm = connection.createStatement();
//        String query = "select * from traveler where Id in (select IdTraveler from tralver_tour where IdTour = '"+idTour+"')";
//        ResultSet resultSet = sttm.executeQuery(query);
//        if(resultSet.next())
//        {
//            return null;
//        }
//        return null;
//    }
//    public List<Traveler> getAllTraveler() throws SQLException, ClassNotFoundException {
//        Connection connection = connector.connectDB();
//        Statement sttm = connection.createStatement();
//        String query = "select * from traveler";
//        ResultSet resultSet = sttm.executeQuery(query);
//        if(resultSet.next())
//        {
//            return null;
//        }
//        return null;
//    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        try {
            Connection connection = connector.connectDB();
            PreparedStatement preparedStatement = connection.prepareStatement("update traveler set Password = ? where Id = ?");
            preparedStatement.setString(1, changePasswordDto.getNewPassword());
            preparedStatement.setInt(2, changePasswordDto.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.warning(e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
