package cnw.Traveler.Model.Dao;

import cnw.utils.Connector;

import java.sql.Connection;
import java.sql.Statement;

public class TralverTourDao {
    private final Connector connector = new Connector();
    public void addTravelerToTour(Integer idTraveler,Integer idTour) throws Exception {
        Connection connection = connector.connectDB();
        Statement sttm = connection.createStatement();
        String query = "insert into tralver_tour values("+idTraveler+","+idTour+")";
        sttm.executeUpdate(query);
    }
    public void removeTravelerFromTour(Integer idTraveler,Integer idTour) throws Exception {
        Connection connection = connector.connectDB();
        Statement sttm = connection.createStatement();
        String query = "delete from tralver_tour where IdTraveler = "+idTraveler+" and IdTour = "+idTour+"";
        sttm.executeUpdate(query);
    }
    public boolean isTravelerInTour(Integer idTraveler,Integer idTour) throws Exception {
        Connection connection = connector.connectDB();
        Statement sttm = connection.createStatement();
        String query = "select * from tralver_tour where IdTraveler = "+idTraveler+" and IdTour = "+idTour+"";
        if(sttm.executeQuery(query).next())
        {
            return true;
        }
        return false;
    }
}
