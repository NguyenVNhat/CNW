package cnw.Admin.Controller;

import cnw.Admin.Models.Bean.Admin;
import cnw.Admin.Models.Bo.AdminBo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin-login")
public class AdminController extends HttpServlet {
    private AdminBo adminBo = new AdminBo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        HttpSession session = req.getSession();;
        if (action.equals("adminlogin"))
        {
            String adminname = req.getParameter("Adminname");
            String password = req.getParameter("Password");
            try {
                Boolean res = adminBo.isValidAdmin(adminname,password);
                if (res)
                {
                    System.out.println(1);
                    session.setAttribute("adminname",adminname);
                    String destination = "/Admin/AdminHome.jsp";
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                    rd.forward(req,resp);
                }
                else{
                    System.out.println(2);
                    String error = "Đăng nhập thất bại";
                    req.setAttribute("Error",error);
                    String destination = "/Admin/login.jsp";
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
                    rd.forward(req,resp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else if (action.equals("logout")) {
            session.removeAttribute("adminname");
            String destination = "/Admin/AdminLogin.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
            rd.forward(req,resp);
        }
    }
    public void setUpDataDashBoard()
    {

    }
}
