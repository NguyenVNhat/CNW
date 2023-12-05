package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Dao.TourDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class TourBo {
    private TourDao tourDao = new TourDao();
    public Tour getDetailTour(Integer Id) throws SQLException, ClassNotFoundException {
        return tourDao.getDetailTour(Id);
    }
    public ArrayList<Tour> getAllTour() throws SQLException, ClassNotFoundException {
        return tourDao.getAllTour();
    }
    public ArrayList<Tour> getUpTour() throws SQLException, ClassNotFoundException {
        return tourDao.getUpTour();
    }
    public ArrayList<Tour> getDownTour() throws SQLException, ClassNotFoundException {
        return tourDao.getDownTour();
    }
    public ArrayList<String> getListAddress(Integer IdTour) throws SQLException, ClassNotFoundException {
        return tourDao.getListAddress(IdTour);
    }
    public Boolean addTour(Tour tour) throws SQLException, ClassNotFoundException {
        return tourDao.addTour(tour);
    }
    public Boolean updateTour(Tour tour) throws SQLException, ClassNotFoundException {
        return tourDao.updateTour(tour);
    }
    public Boolean deleteTour(Integer Id) throws SQLException, ClassNotFoundException {
        return tourDao.deleteTour(Id);
    }
}
