package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Instructor;
import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Bo.InstructorBo;
import cnw.Admin.Models.Bo.TourBo;
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

@WebServlet("/tour")
public class TourController extends HttpServlet
{
    private final TourBo tourBo = new TourBo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("getallTour"))
        {
            try {
                getAllTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if(action.equals("getUpTour"))
        {
            try {
                getUpTour(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if(action.equals("getDownTour"))
        {
            try {
                getDownTour(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("getdetailTour")){
            try {
                getDetailTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("ToupdateTour")){
            try {

                Integer IdTour = Integer.parseInt(req.getParameter("Id")) ;
                InstructorBo instructorBo = new InstructorBo();
                ArrayList<Instructor> instructors  = instructorBo.getListIntructor(IdTour);
                req.setAttribute("detailIns",instructors);
                Tour tour = tourBo.getDetailTour(IdTour);
                req.setAttribute("detailTour",tour);

                String destination = "/Admin/formUpdateTour.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        else if (action.equals("updateTour")){
            try {
                updateTour(req, resp);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("TodeleteTour")){
            try {
                Integer IdTour = Integer.parseInt(req.getParameter("Id")) ;
                Tour tour = tourBo.getDetailTour(IdTour);
                req.setAttribute("detailTour",tour);
                String destination = "/Admin/formDelete.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("deleteTour")){

        }
        else if (action.equals("ToAddTour")){
            InstructorBo instructorBo = new InstructorBo();
            ArrayList<Instructor> instructors  = null;
            try {
                instructors = instructorBo.getAllIntructor();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("detailIns",instructors);

            String destination = "/Admin/formAddTour.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(req,resp);
        }
        else if (action.equals("addTour")){
            try {
                addTour(req, resp);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void updateTour(HttpServletRequest req, HttpServletResponse resp) throws ParseException, SQLException, ClassNotFoundException, ServletException, IOException {
         Integer Id = Integer.parseInt(req.getParameter("Id"));
         String Instructor =req.getParameter("Intructor");
         Integer Price= Integer.parseInt(req.getParameter("Price"));
         Integer ToTalTime =Integer.parseInt(req.getParameter("TotalTime"));
         String TimeStart = req.getParameter("TimeStart");
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         Date timeStartDate = dateFormat.parse(TimeStart);
         Boolean Status = Boolean.parseBoolean(req.getParameter("Status"));
         Tour tour = new Tour(Id,Instructor,Price,ToTalTime,timeStartDate,Status);
            Boolean res = tourBo.updateTour(tour);
            if(res)
            {
                getAllTour(req, resp);
            }
            else{
                String error = "Cập nhật thất bại";
                req.setAttribute("error",error);
                InstructorBo instructorBo = new InstructorBo();
                ArrayList<Instructor> instructors  = instructorBo.getListIntructor(Id);
                req.setAttribute("detailIns",instructors);
                Tour tour1 = tourBo.getDetailTour(Id);
                req.setAttribute("detailTour",tour1);
                String destination = "/Admin/formUpdateTour.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);
            }

    }
    public void addTour(HttpServletRequest req, HttpServletResponse resp) throws ParseException, SQLException, ClassNotFoundException, ServletException, IOException {
        Integer Id = Integer.parseInt(req.getParameter("Id"));
        String Instructor =req.getParameter("Intructor");
        Integer Price= Integer.parseInt(req.getParameter("Price"));
        Integer ToTalTime =Integer.parseInt(req.getParameter("TotalTime"));

        String TimeStart = req.getParameter("TimeStart");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timeStartDate = dateFormat.parse(TimeStart);

        Boolean Status = Boolean.parseBoolean(req.getParameter("Status"));
        Tour tour = new Tour(Id,Instructor,Price,ToTalTime,timeStartDate,Status);
        Boolean res = tourBo.addTour(tour);
        if(res)
        {
            getAllTour(req, resp);
        }
        else{
            String error = "Thêm thất bại";
            req.setAttribute("error",error);
            String destination = "/Admin/formAddTour.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(req,resp);
        }

    }
    public void getAllTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {

        ArrayList<Tour> tours = tourBo.getAllTour();
        for (Tour tour: tours
             ) {
            ArrayList<String> listAddress = tourBo.getListAddress(tour.getId());
            tour.setListAddress(listAddress);
        }
        req.setAttribute("pageId", "1");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getDetailTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer IdTour = Integer.parseInt(req.getParameter("Id")) ;
        Tour tour = tourBo.getDetailTour(IdTour);
        ArrayList<String> listAddress = tourBo.getListAddress(IdTour);
        tour.setListAddress(listAddress);
        req.setAttribute("detailTour",tour);
        String destination = "/Admin/detailTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getUpTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArrayList<Tour> tours = tourBo.getUpTour();
        for (Tour tour: tours
        ) {
            ArrayList<String> listAddress = tourBo.getListAddress(tour.getId());
            tour.setListAddress(listAddress);
        }
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getDownTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArrayList<Tour> tours = tourBo.getDownTour();
        for (Tour tour: tours
        ) {
            ArrayList<String> listAddress = tourBo.getListAddress(tour.getId());
            tour.setListAddress(listAddress);
        }
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
}
