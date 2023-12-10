package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Bean.Traveler;
import cnw.Admin.Models.Dao.Travel_TourDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Travel_TourBo {
    private Travel_TourDao tourBo = new Travel_TourDao();
    public ArrayList<Travel_Tour> getALlTravelTour() throws SQLException, ClassNotFoundException {
        return tourBo.getALlTravelTour();
    }
    public ArrayList<Travel_Tour> getUpTravelTour() throws SQLException, ClassNotFoundException {
        return tourBo.getUpTravelTour();
    }
    public ArrayList<Travel_Tour> getDownTravelTour() throws SQLException, ClassNotFoundException {
        return tourBo.getDownTravelTour();
    }
    public ArrayList<Travel_Tour> findTour(String textsearch,String pageId) throws SQLException, ClassNotFoundException {
        return tourBo.findTravelTour(textsearch, pageId);
    }
    public ArrayList<Travel_Tour> findTourDay(String textsearch) throws SQLException, ClassNotFoundException {
        return tourBo.findTourDay(textsearch);
    }
    public ArrayList<Travel_Tour> getCurentTravelTour() throws SQLException, ClassNotFoundException {
        return tourBo.getCurrentTravelTour();
    }
    public Travel_Tour getDetailTravelTour(Integer IdTour) throws SQLException, ClassNotFoundException {
        return tourBo.getDetailTour(IdTour);
    }
    public Integer getTraveler_Tour_count() throws SQLException, ClassNotFoundException {
        return tourBo.getTraveler_Tour_count();
    }
    public ArrayList<Integer> revenueWeek() throws SQLException, ClassNotFoundException {
        return  tourBo.revenueWeek();
    }
    public ArrayList<Integer> revenueWeek(Date date) throws SQLException, ClassNotFoundException {
        return  tourBo.revenueWeek(date);
    }
    public Integer revenueAllOfWeek() throws SQLException, ClassNotFoundException {
        return tourBo.revenueAllOfWeek();
    }
    public Integer revenueAllOfMonth() throws SQLException, ClassNotFoundException {
        return tourBo.revenueAllOfMonth();
    }
    public void DeleteTravelOnTour(Integer IdTour,Integer IdTraveler) throws SQLException, ClassNotFoundException {
        tourBo.DeleteTravelOnTour(IdTour,IdTraveler);
    }
    public ArrayList<Traveler> getListTraveler() throws SQLException, ClassNotFoundException {
        return tourBo.getListTraveler();
    }
    public Boolean AddTravelOnTour(Integer IdTour,Integer IdTraveler) throws SQLException, ClassNotFoundException {
        return tourBo.AddTravelOnTour(IdTour,IdTraveler);
    }
    public void Delete(Integer IdTour) throws SQLException, ClassNotFoundException {
        tourBo.Delete(IdTour);
    }
}
