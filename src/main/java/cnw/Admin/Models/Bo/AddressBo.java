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
}
