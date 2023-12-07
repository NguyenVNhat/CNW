package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Bean.Instructor;
import cnw.Admin.Models.Dao.IntructorDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class InstructorBo {
    private IntructorDao intructorDao = new IntructorDao();
    public ArrayList<Instructor> getListIntructor(Integer IdTour) throws SQLException, ClassNotFoundException {
        return intructorDao.getListIntructor(IdTour);
    }
    public ArrayList<Instructor> getAllIntructor() throws SQLException, ClassNotFoundException {
        return intructorDao.getAllIntructor();
    }
}
