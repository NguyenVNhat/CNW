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
    public Integer getInstructor_count() throws SQLException, ClassNotFoundException {
        return intructorDao.getInstructor_count();
    }
    public void Add(Instructor instructor) throws SQLException, ClassNotFoundException {
        intructorDao.Add(instructor);
    }
    public Instructor getDetail(Integer Id) throws SQLException, ClassNotFoundException {
        return intructorDao.getDetail(Id);
    }
    public void Update(Integer Id , String name, Integer age,String email,String phone) throws SQLException, ClassNotFoundException {
        intructorDao.Update(Id,name,age,email,phone);
    }
}
