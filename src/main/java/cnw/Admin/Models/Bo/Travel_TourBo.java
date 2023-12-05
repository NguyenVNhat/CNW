package cnw.Admin.Models.Bo;

import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Dao.Travel_TourDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class Travel_TourBo {
    private Travel_TourDao tourBo = new Travel_TourDao();
    public ArrayList<Travel_Tour> getALlTravelTour() throws SQLException, ClassNotFoundException {
        return tourBo.getALlTravelTour();
    }
}
