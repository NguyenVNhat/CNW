package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Bean.Instructor;
import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Dao.TourDao;
import cnw.Admin.Models.Dao.Travel_TourDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class TourBo {
    private TourDao tourDao = new TourDao();
    public Tour getDetailTour(Integer Id) throws SQLException, ClassNotFoundException {
        return tourDao.getDetailTour(Id);
    }
    public Integer getTour_count() throws SQLException, ClassNotFoundException {
        return tourDao.getTour_count();
    }
    public ArrayList<Integer> getTourWeek() throws SQLException, ClassNotFoundException {
        return tourDao.getTourWeek();
    }
    public ArrayList<Tour> findTourDay(String textSearch) throws SQLException, ClassNotFoundException {
        return tourDao.findTourDay(textSearch);
    }
    public ArrayList<Tour> findTour(String textSearch,String pageID) throws SQLException, ClassNotFoundException {
        return tourDao.findTour(textSearch, pageID);
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
    public ArrayList<Tour> getCurentTour() throws SQLException, ClassNotFoundException {
        return tourDao.getCurrentTour();
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
    public void deleteTour(Integer Id) throws SQLException, ClassNotFoundException {

        Travel_TourDao travelTourDao = new Travel_TourDao();
        travelTourDao.Delete(Id);
            tourDao.deleteTour(Id);
    }

}
