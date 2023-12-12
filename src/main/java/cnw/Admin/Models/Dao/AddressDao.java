package cnw.Admin.Models.Dao;

import cnw.Admin.Models.Bean.Address;
import cnw.utils.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddressDao {
    private Connector connector = new Connector();
    public Integer getAddress_count() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) as count FROM address";
        ResultSet resultSet = statement.executeQuery(query);
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }
    public Address getDetailAddress(Integer IdAddress) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "Select * from Address where Id = '"+IdAddress+"'";
        ResultSet resultSet = statement.executeQuery(query);
        Address address = new Address(0,"0");
        while (resultSet.next())
        {
            Integer Id = resultSet.getInt("Id");
            String AddressName = resultSet.getString("Address");
            address.setId(Id);
            address.setAddressName(AddressName);
         }
        return address;

    }
    public ArrayList<Address> getAddressByIdTour(Integer IdTour) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "SELECT address.Id,address.Address FROM TOUR \n" +
                "JOIN tour_address ON tour_address.IdTour = tour.Id \n" +
                "JOIN address ON tour_address.IdAddress = address.Id \n" +
                "WHERE tour.Id = '"+IdTour+"'";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Address> addresses = new ArrayList<>();
        while (resultSet.next())
        {
            Integer Id = resultSet.getInt("Id");
            String AddressName = resultSet.getString("Address");
            addresses.add(new Address(Id,AddressName));
        }
        return addresses;
    }
    public ArrayList<Address> getAllAddress() throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "select * from Address";
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Address> addresses = new ArrayList<>();
        while (resultSet.next())
        {
            Integer Id = resultSet.getInt("Id");
            String AddressName = resultSet.getString("Address");
            addresses.add(new Address(Id,AddressName));
        }
        return addresses;
    }
    public void Add(Address newAddress) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "INSERT INTO `address`(`Id`, `Address`) VALUES ('"+newAddress.getId()+"','"+newAddress.getAddressName()+"')";
        int res = statement.executeUpdate(query);
    }
    public void Update(Integer Id,String addressname) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "UPDATE `address` SET `Address`='"+addressname+"' WHERE `Id`='"+Id+"'";
        int res = statement.executeUpdate(query);
    }
    public void Delete(Integer IdAddress) throws SQLException, ClassNotFoundException {
        Connection connection = connector.connectDB();
        Statement statement = connection.createStatement();
        String query = "Delete from tour_address where IdAddress = '"+IdAddress+"'";
        int res = statement.executeUpdate(query);
        String query1 = "Delete from address where Id = '"+IdAddress+"'";
        int res1 = statement.executeUpdate(query1);
    }

}
