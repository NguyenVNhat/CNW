package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Dao.Tour_AddressDao;

import java.sql.SQLException;

public class Tour_AddressBo {
    private Tour_AddressDao tourAddressDao = new Tour_AddressDao();
    public void Add(Integer IdTour,Integer IdAddress) throws SQLException, ClassNotFoundException {
        tourAddressDao.Add(IdTour, IdAddress);
    }
    public void Update( Integer Id,String[] selectedAddresses) throws SQLException, ClassNotFoundException {
        tourAddressDao.Delete(Id);
        for (String address: selectedAddresses
        ) {
            Integer IdAddress = Integer.valueOf(address);
            tourAddressDao.Add(Id,IdAddress);
        }

    }

}