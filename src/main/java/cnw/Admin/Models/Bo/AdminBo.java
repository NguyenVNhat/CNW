package cnw.Admin.Models.Bo;

import cnw.Admin.Models.AdminNotFoundException;
import cnw.Admin.Models.Bean.Admin;
import cnw.Admin.Models.Dao.AdminDao;

import java.sql.SQLException;

public class AdminBo {
    private final AdminDao adminDao = new AdminDao();
    public Boolean isValidAdmin(String username,String password) throws SQLException, ClassNotFoundException {
        return adminDao.isExitAdmin(username, password);
    }
    public Admin getAdminByUsername(String username) throws SQLException, ClassNotFoundException, AdminNotFoundException {
        return adminDao.getAdminByUsername(username).orElseThrow(() -> new AdminNotFoundException(username));
    }

}
