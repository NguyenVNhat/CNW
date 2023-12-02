package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Dao.AdminDao;

import java.sql.SQLException;

public class AdminBo {
    private AdminDao adminDao = new AdminDao();
    public Boolean isValidAdmin(String adminname,String password) throws SQLException, ClassNotFoundException {
        return adminDao.isExitAdmin(adminname, password);
    }

}
