package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Address;
import cnw.Admin.Models.Bean.Instructor;
import cnw.Admin.Models.Bo.AddressBo;
import cnw.Admin.Models.Bo.InstructorBo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/insaddress")
public class InstructorAndAddressController extends HttpServlet {
    private InstructorBo instructorBo = new InstructorBo();
    private AddressBo addressBo = new AddressBo();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("insandaddress"))
        {
            try {
                GoToJSP(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addAddress")) {
            try {
                AddAddress(req, resp);
                GoToJSP(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        else if (action.equals("addIns")) {
            try {
                AddInstructor(req, resp);
                GoToJSP(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (action.equals("ToupdateAddress")) {

            try {
                Integer IdAddress = Integer.parseInt(req.getParameter("Id"));
                Address address = addressBo.getDetailAddress(IdAddress);
                req.setAttribute("address",address);
                String destination = "/Admin/formUpdateAddress.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else if (action.equals("ToupdateIns")) {

            try {
                Integer IdIns = Integer.parseInt(req.getParameter("Id"));
                Instructor instructors = instructorBo.getDetail(IdIns);
                req.setAttribute("instructors",instructors);
                String destination = "/Admin/formUpdateIns.jsp";
                RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                rd.forward(req,resp);

            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("updateAddress")) {
            try {
                UpdateAddress(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("updateIns")) {
            try {
                UpdateIns(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else if (action.equals("deleteAddress")) {
            try {
                deleteAddress(req, resp);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void deleteAddress(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String[] selectedAddresses = req.getParameterValues("IdAddress");
        for (String item: selectedAddresses
             ) {
            Integer Id = Integer.parseInt(item);
            addressBo.Delete(Id);
        }
        GoToJSP(req, resp);
    }
    public void UpdateAddress(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer Id  = Integer.parseInt(req.getParameter("Id"));
        String addressname = req.getParameter("AddressName");

        addressBo.Update(Id,addressname);
        GoToJSP(req, resp);
    }
    public void UpdateIns(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        Integer Id  = Integer.parseInt(req.getParameter("Id"));
        String Name = req.getParameter("Name");
        Integer Age  = Integer.parseInt(req.getParameter("Age"));
        String Email = req.getParameter("Email");
        String Phone = req.getParameter("Phone");
        instructorBo.Update(Id,Name,Age,Email,Phone);
        GoToJSP(req, resp);
    }
    public void AddInstructor(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        Integer Id = Integer.parseInt(req.getParameter("Id"));
        String Name = req.getParameter("Name");
        Integer Age = Integer.parseInt(req.getParameter("Age"));
        String Email = req.getParameter("Email");
        String Phone = req.getParameter("Phone");

        Instructor instructor = new Instructor(Id,Name,Age,Email,Phone);
        instructorBo.Add(instructor);
    }
    public void AddAddress(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException {
        Integer Id = Integer.parseInt(req.getParameter("Id"));
        String addressname = req.getParameter("AddressName");
        Address address =new Address(Id,addressname);
        addressBo.Add(address);
    }
    public void GoToJSP(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ClassNotFoundException, ServletException, IOException {
        ArrayList<Instructor> instructors = getListInstructor();
        ArrayList<Address> addresses = getListAddress();
        req.setAttribute("instructors",instructors);
        req.setAttribute("addresses",addresses);
        String destination = "/Admin/List_Instructor_Address.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req,resp);
    }
    public ArrayList<Instructor> getListInstructor() throws SQLException, ClassNotFoundException {
        return instructorBo.getAllIntructor();
    }
    public ArrayList<Address> getListAddress() throws SQLException, ClassNotFoundException {
        return addressBo.getAllAddress();
    }
}
