package cnw.Admin.Models.Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tour_AddressDao {
    private Connector connector = new Connector();
    public void Add(Integer IdTour,Integer IdAddress) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query ="INSERT INTO `tour_address`(`IdTour`, `IdAddress`) VALUES ('"+IdTour+"','"+IdAddress+"')";
        int res = statement.executeUpdate(query);
    }
}
