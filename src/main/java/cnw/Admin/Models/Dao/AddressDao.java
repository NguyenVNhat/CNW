package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddressDao {
    private Connector connector = new Connector();
    public ArrayList<Address> getAllAddress() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "select * from Address";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Address> addresses = new ArrayList<>();
        while (resultSet.next())
        {
            Integer Id = resultSet.getInt("Id");
            String AddressName = resultSet.getString("AddressName");
            addresses.add(new Address(Id,AddressName));
        }
        return addresses;
    }
}
