package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Bean.Address;
import cnw.Admin.Models.Dao.AddressDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddressBo {
    private AddressDao addressDao =new AddressDao();
    public ArrayList<Address> getAllAddress() throws SQLException, ClassNotFoundException {
        return addressDao.getAllAddress();
    }
    public Integer getAddress_count() throws SQLException, ClassNotFoundException {
        return addressDao.getAddress_count();
    }
    public ArrayList<Address> getAddressByIdTour(Integer IdTour) throws SQLException, ClassNotFoundException {
        return addressDao.getAddressByIdTour(IdTour);
    }
    public void Add(Address newAddress) throws SQLException, ClassNotFoundException {
        addressDao.Add(newAddress);
    }
    public Address getDetailAddress(Integer IdAddress) throws SQLException, ClassNotFoundException {
        return addressDao.getDetailAddress(IdAddress);
    }
    public void Update(Integer Id,String addressname) throws SQLException, ClassNotFoundException {
        addressDao.Update(Id,addressname);
    }
    public void Delete(Integer IdAddress) throws SQLException, ClassNotFoundException {
        addressDao.Delete(IdAddress);
    }
}
