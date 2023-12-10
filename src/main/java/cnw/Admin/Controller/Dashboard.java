package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Bo.AddressBo;
import cnw.Admin.Models.Bo.InstructorBo;
import cnw.Admin.Models.Bo.TourBo;
import cnw.Admin.Models.Bo.Travel_TourBo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/dashboard")
public class
Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Integer tourCount = getTour_count();
            Integer traveltourCount = getTraveler_Tour_count();
            Integer addressCount = getAddress_count();
            Integer insCount = getInstructor_count();
            ArrayList<Integer> revenueweek = new ArrayList<>();
            if(action.equals("dashboardView"))
            {
                revenueweek = revenueWeek();
            } else if (action.equals("revenue-week")) {
                String TimeStart = req.getParameter("time");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date timeStartDate = dateFormat.parse(TimeStart);
                revenueweek = revenueWeek(timeStartDate);
            }

            ArrayList<Integer> getTourWeek = getTourWeek();
            Integer revenueallofweek = revenueAllOfWeek();
            Integer revenueallofmonth = revenueAllOfMonth();
            ArrayList<Travel_Tour> getListTravel_Tour = getListTravel_Tour();

            req.setAttribute("tourCount",tourCount);
            req.setAttribute("traveltourCount",traveltourCount);
            req.setAttribute("addressCount",addressCount);
            req.setAttribute("insCount",insCount);
            req.setAttribute("revenueweek",revenueweek);
            req.setAttribute("getTourWeek",getTourWeek);
            req.setAttribute("revenueallofweek",revenueallofweek);
            req.setAttribute("revenueallofmonth",revenueallofmonth);
            req.setAttribute("getListTravel_Tour",getListTravel_Tour);

            String destination = "/Admin/Dashboard.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(req,resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
    public ArrayList<Integer> getTourWeek() throws SQLException, ClassNotFoundException {
        TourBo tourBo = new TourBo();
        return tourBo.getTourWeek();
    }
    public Integer getTour_count() throws SQLException, ClassNotFoundException {
        TourBo tourBo = new TourBo();
        return tourBo.getTour_count();
    }
    public Integer getTraveler_Tour_count() throws SQLException, ClassNotFoundException {
        Travel_TourBo travelTourBo = new Travel_TourBo();
        return travelTourBo.getTraveler_Tour_count();
    }
    public Integer getAddress_count() throws SQLException, ClassNotFoundException {
        AddressBo addressBo = new AddressBo();
        return addressBo.getAddress_count();
    }
    public Integer getInstructor_count() throws SQLException, ClassNotFoundException {
        InstructorBo instructorBo = new InstructorBo();
        return instructorBo.getInstructor_count();
    }
    public ArrayList<Integer> revenueWeek() throws SQLException, ClassNotFoundException {
        Travel_TourBo travelTourBo =new Travel_TourBo();
        return travelTourBo.revenueWeek();
    }
    public ArrayList<Integer> revenueWeek(Date date) throws SQLException, ClassNotFoundException {
        Travel_TourBo travelTourBo =new Travel_TourBo();
        return travelTourBo.revenueWeek(date);
    }
    public Integer revenueAllOfWeek() throws SQLException, ClassNotFoundException {
        Travel_TourBo travelTourBo =new Travel_TourBo();
        return travelTourBo.revenueAllOfWeek();
    }
    public Integer revenueAllOfMonth() throws SQLException, ClassNotFoundException {
        Travel_TourBo travelTourBo =new Travel_TourBo();
        return travelTourBo.revenueAllOfMonth();
    }
    public ArrayList<Travel_Tour> getListTravel_Tour() throws SQLException, ClassNotFoundException {
        Travel_TourBo tourBo = new Travel_TourBo();
        return  tourBo.getALlTravelTour();
    }
}
