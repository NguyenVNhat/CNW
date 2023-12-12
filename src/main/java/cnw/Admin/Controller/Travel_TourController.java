package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Bean.Traveler;
import cnw.Admin.Models.Bo.TourBo;
import cnw.Admin.Models.Bo.Travel_TourBo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@WebServlet("/traveltour")
public class Travel_TourController extends HttpServlet {
    private Travel_TourBo tourBo = new Travel_TourBo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("getallTravelTour"))
        {
            try {
                getAllTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("getUpTravelTour")) {
            try {
                getUpTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("getDownTravelTour")) {
            try {
                getDownTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("getCurrentTravelTour")) {
            try {
                getCurrentTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("getdetailTour")) {
            try {
                getDetailTour(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("ToupdateTour")) {
            try {
                ToformUpdate(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("deleteTravelInTour")) {
            try {
                deleteTravelInTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addTravelInTour")) {
            try {
                addTravelInTour(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("deleteAll")) {
            try {
                deleteALl(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("findTour")) {
            try {
                findTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("findTourDay")) {
            try {
                findYourTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("ToAddTour")) {
            TourBo tourBo1 = new TourBo();

            try {
                ArrayList<Tour> tours = tourBo1.getAllTour();
                for (Tour tour: tours
                ) {
                    ArrayList<String> listAddress = tourBo1.getListAddress(tour.getId());
                    tour.setListAddress(listAddress);
                }
                ArrayList<Traveler> travelers = tourBo.getListTraveler();
                req.setAttribute("travelers",travelers);
                req.setAttribute("tours",tours);
                String destination = "/Admin/formAddTravelTour.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (action.equals("AddTravelTour")) {
            try {
                AddTravelTour(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void AddTravelTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer IdTour = Integer.parseInt(req.getParameter("IdTour"));
        String[] selectedAddresses = req.getParameterValues("IdTraveler");
        for (String item :selectedAddresses
             ) {
            Integer travel = Integer.parseInt(item);
            Boolean res =  tourBo.AddTravelOnTour(IdTour,travel);
        }
        getAllTour(req, resp);
    }
    public void findYourTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String textsearch = req.getParameter("textsearch");
        String pageId = req.getParameter("typeSearch");
        ArrayList<Travel_Tour> tours = tourBo.findTourDay(textsearch);

        req.setAttribute("pageId", "1");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void findTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String textsearch = req.getParameter("textsearch");
        String pageId = req.getParameter("typeSearch");
        ArrayList<Travel_Tour> tours = tourBo.findTour(textsearch,pageId);

        req.setAttribute("pageId", "1");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void deleteALl(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String[] selectedAddresses = req.getParameterValues("IdTour");
        for (String item:selectedAddresses
        ) {
            Integer travelertour = Integer.parseInt(item);
            tourBo.Delete(travelertour);
        }
        getAllTour(req, resp);
    }
    public void addTravelInTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer IdTour = Integer.parseInt(req.getParameter("IdTour"));
        String[] selectedAddresses = req.getParameterValues("IdTravel1");
        for (String item:selectedAddresses
        ) {
            Integer traveler = Integer.parseInt(item);
            tourBo.AddTravelOnTour(IdTour,traveler);
        }

        Travel_Tour tours = tourBo.getDetailTravelTour(IdTour);
        ArrayList<Traveler> travelers = tourBo.getListTraveler();
        req.setAttribute("tours",tours);
        req.setAttribute("travelers",travelers);
        String destination = "/Admin/updateTravelTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void deleteTravelInTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer IdTour = Integer.parseInt(req.getParameter("IdTour"));
        String[] selectedAddresses = req.getParameterValues("IdTravel");
        for (String item:selectedAddresses
             ) {
            Integer Idtraveler = Integer.parseInt(item);
            tourBo.DeleteTravelOnTour(IdTour,Idtraveler);
        }
        Travel_Tour tours = tourBo.getDetailTravelTour(IdTour);
        ArrayList<Traveler> travelers = tourBo.getListTraveler();
        if(tours != null) {
            req.setAttribute("tours", tours);
            req.setAttribute("travelers", travelers);
            String destination = "/Admin/updateTravelTour.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(req, resp);
        }
        else{
            getAllTour(req, resp);
        }
    }
    public void ToformUpdate(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer IdTour = Integer.parseInt(req.getParameter("Id"));
        Travel_Tour tours = tourBo.getDetailTravelTour(IdTour);
        ArrayList<Traveler> travelers = tourBo.getListTraveler();
        req.setAttribute("tours",tours);
        req.setAttribute("travelers",travelers);
        String destination = "/Admin/updateTravelTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getDetailTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer InTour = Integer.parseInt(req.getParameter("Id"));
        Travel_Tour tours = tourBo.getDetailTravelTour(InTour);
        req.setAttribute("tours",tours);
        String destination = "/Admin/detailTravelTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getAllTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {

        ArrayList<Travel_Tour> tours = tourBo.getALlTravelTour();
        req.setAttribute("pageId", "1");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getUpTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {

        ArrayList<Travel_Tour> tours = tourBo.getUpTravelTour();
        req.setAttribute("pageId", "2");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getDownTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {

        ArrayList<Travel_Tour> tours = tourBo.getDownTravelTour();
        req.setAttribute("pageId", "3");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getCurrentTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {

        ArrayList<Travel_Tour> tours = tourBo.getCurentTravelTour();
        req.setAttribute("pageId", "4");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
}
