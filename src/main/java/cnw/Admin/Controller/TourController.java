package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Address;
import cnw.Admin.Models.Bean.Instructor;
import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Bo.AddressBo;
import cnw.Admin.Models.Bo.InstructorBo;
import cnw.Admin.Models.Bo.TourBo;
import cnw.Admin.Models.Bo.Tour_AddressBo;
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
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if(action.equals("getDownTour"))
        {
            try {
                getDownTour(req,resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if(action.equals("getCurrentTour"))
        {
            try {
                getCurrentTour(req,resp);
            } catch (SQLException | ClassNotFoundException e) {
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
                AddressBo addressBo = new AddressBo();

                ArrayList<Instructor> instructors  = instructorBo.getListIntructor(IdTour);
                Tour tour = tourBo.getDetailTour(IdTour);
                ArrayList<Address> addresses  = addressBo.getAddressByIdTour(IdTour);

                ArrayList<Instructor> instructorALl  = null;
                ArrayList<Address> addressesAll  = null;

                instructorALl = instructorBo.getAllIntructor();
                addressesAll = addressBo.getAllAddress();

                req.setAttribute("instructorALl",instructorALl);
                req.setAttribute("addressesAll",addressesAll);


                req.setAttribute("detailIns",instructors);
                req.setAttribute("detailAddress",addresses);
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
            } catch (ParseException | SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("TodeleteTour")){
            try {
                Integer IdTour = Integer.parseInt(req.getParameter("Id")) ;
                InstructorBo instructorBo = new InstructorBo();
                AddressBo addressBo = new AddressBo();

                ArrayList<Instructor> instructors  = instructorBo.getListIntructor(IdTour);
                Tour tour = tourBo.getDetailTour(IdTour);
                ArrayList<Address> addresses  = addressBo.getAddressByIdTour(IdTour);

                ArrayList<Instructor> instructorALl  = null;
                ArrayList<Address> addressesAll  = null;

                instructorALl = instructorBo.getAllIntructor();
                addressesAll = addressBo.getAllAddress();

                req.setAttribute("instructorALl",instructorALl);
                req.setAttribute("addressesAll",addressesAll);


                req.setAttribute("detailIns",instructors);
                req.setAttribute("detailAddress",addresses);
                req.setAttribute("detailTour",tour);

                String destination = "/Admin/formDeleteTour.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("deleteTour")){
            try {
                deleteTour(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("ToAddTour")){
            InstructorBo instructorBo = new InstructorBo();
            AddressBo addressBo = new AddressBo();
            ArrayList<Instructor> instructors  = null;
            ArrayList<Address> addresses  = null;
            try {
                instructors = instructorBo.getAllIntructor();
                addresses = addressBo.getAllAddress();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("detailIns",instructors);
            req.setAttribute("detailAddress",addresses);
            String destination = "/Admin/formAddTour.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(req,resp);
        }
        else if (action.equals("addTour")){
            try {
                addTour(req, resp);
            } catch (ParseException | SQLException | ClassNotFoundException e) {
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
                findTourDay(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("deleteAll")) {
            try {
                deleteAll(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String[] selectedItem = req.getParameterValues("IdSelected");
        for (String item: selectedItem
             ) {
            Integer IdTour = Integer.parseInt(item);
            tourBo.deleteTour(IdTour);
        }
        getAllTour(req, resp);
    }
    public void deleteTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer IdTour = Integer.parseInt(req.getParameter("IdTour"));
        tourBo.deleteTour(IdTour);
        getAllTour(req, resp);
    }
    public void findTourDay(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String textsearch = req.getParameter("textsearch");
        String pageId = req.getParameter("pageId");
        ArrayList<Tour> tours = tourBo.findTourDay(textsearch);
        for (Tour tour: tours
        ) {
            ArrayList<String> listAddress = tourBo.getListAddress(tour.getId());
            tour.setListAddress(listAddress);
        }
        req.setAttribute("pageId", pageId);
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void findTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String textsearch = req.getParameter("textsearch");
        String pageId = req.getParameter("typeSearch");
        ArrayList<Tour> tours = tourBo.findTour(textsearch,pageId);
        for (Tour tour: tours
        ) {
            ArrayList<String> listAddress = tourBo.getListAddress(tour.getId());
            tour.setListAddress(listAddress);
        }
        req.setAttribute("pageId", pageId);
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void updateTour(HttpServletRequest req, HttpServletResponse resp) throws ParseException, SQLException, ClassNotFoundException, ServletException, IOException {
        Integer Id =Integer.parseInt(req.getParameter("Id"));
        Integer Price = Integer.parseInt(req.getParameter("Price"));
        Integer TotalTime = Integer.parseInt(req.getParameter("TotalTime"));

        String TimeStart = req.getParameter("TimeStart");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timeStartDate = dateFormat.parse(TimeStart);
        String[] selectedAddresses = req.getParameterValues("IdAddress");
        Integer IdInstructor =  Integer.parseInt(req.getParameter("IdInstructor"));
        String Name = req.getParameter("Name");
        String Description = req.getParameter("Description");

        Tour tour = new Tour(Id,IdInstructor,Price,TotalTime,timeStartDate,true,Name,Description);

        Tour_AddressBo addressBo = new Tour_AddressBo();
            Boolean res = tourBo.updateTour(tour);
            if(res)
            {

                addressBo.Update(Id,selectedAddresses);

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
        Integer Id =Integer.parseInt(req.getParameter("Id"));
        Integer Price = Integer.parseInt(req.getParameter("Price"));
        Integer TotalTime = Integer.parseInt(req.getParameter("TotalTime"));

        String TimeStart = req.getParameter("TimeStart");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timeStartDate = dateFormat.parse(TimeStart);
        String[] selectedAddresses = req.getParameterValues("IdAddress");
        Integer IdInstructor =  Integer.parseInt(req.getParameter("IdInstructor"));
        String Name = req.getParameter("Name");
        String Description = req.getParameter("Description");

        Tour_AddressBo addressBo = new Tour_AddressBo();

        Tour tour = new Tour(Id,IdInstructor,Price,TotalTime,timeStartDate,true,Name,Description);
        Boolean res = tourBo.addTour(tour);
        if(res)
        {
            for (String address: selectedAddresses
            ) {
                Integer IdAddress = Integer.valueOf(address);
                addressBo.Add(Id,IdAddress);
            }
            getAllTour(req, resp);
        }
        else{
            String error = "Thêm tour mới thất bại";
            req.setAttribute("error",error);

            InstructorBo instructorBo = new InstructorBo();
            AddressBo addressBo1 = new AddressBo();
            ArrayList<Instructor> instructors  = null;
            ArrayList<Address> addresses  = null;
            try {
                instructors = instructorBo.getAllIntructor();
                addresses = addressBo1.getAllAddress();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("detailIns",instructors);
            req.setAttribute("detailAddress",addresses);
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
        req.setAttribute("pageId", "2");
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
        req.setAttribute("pageId", "3");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public void getCurrentTour(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArrayList<Tour> tours = tourBo.getCurentTour();
        for (Tour tour: tours
        ) {
            ArrayList<String> listAddress = tourBo.getListAddress(tour.getId());
            tour.setListAddress(listAddress);
        }
        req.setAttribute("pageId", "4");
        req.setAttribute("tours",tours);
        String destination = "/Admin/ListTour.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
}
