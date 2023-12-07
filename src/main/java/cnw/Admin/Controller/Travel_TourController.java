package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Bean.Travel_Tour;
import cnw.Admin.Models.Bo.Travel_TourBo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
        }
    }
    public void getAllTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {

        ArrayList<Travel_Tour> tours = tourBo.getALlTravelTour();
        req.setAttribute("pageId", "1");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTravel_Tour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
}
