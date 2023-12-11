package cnw.utils.filter.auth;

import cnw.Admin.Models.Bean.Admin;
import cnw.Admin.Models.Bo.AdminBo;
import cnw.Traveler.Model.Bean.Traveler;
import cnw.Traveler.Model.Bo.TravelerBo;
import cnw.utils.viewdto.SummaryProfile;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(dispatcherTypes = DispatcherType.REQUEST)
public class AuthorizationFilter extends HttpFilter {

    AdminBo adminBo = new AdminBo();
    TravelerBo travelerBo = new TravelerBo();
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        UserCredential userCredential = (UserCredential) request.getAttribute("userCredential");
        if(userCredential == null){
            chain.doFilter(request, response);
            return;
        }
        if (userCredential.getRole() == Role.ADMIN)
        {
            try {
                Admin admin = adminBo.getAdminByUsername(userCredential.getUsername());
                SummaryProfile summaryProfile = new SummaryProfile(admin);
                session.setAttribute("isAuthorized", true);
                session.setAttribute("summaryProfile", summaryProfile);
                redirectToHome(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (userCredential.getRole() == Role.TRAVELER)
        {
            try {
                Traveler traveler = travelerBo.getTravelerByUsername(userCredential.getUsername());
                SummaryProfile summaryProfile = new SummaryProfile(traveler);
                session.setAttribute("isAuthorized", true);
                session.setAttribute("summaryProfile", summaryProfile);
                session.setMaxInactiveInterval(60 *60);
                redirectToHome(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            throw new ServletException("userCredential is not null");
        }

    }
    public void redirectToHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        if (uri.contains("/admin/"))
            resp.sendRedirect(req.getContextPath() + "/admin");
        else if (uri.contains("/traveler/"))
            resp.sendRedirect(req.getContextPath() + "/traveler");
    }

//    public void redirectToTravelerLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.sendRedirect(req.getContextPath() + "/traveler/login");
//    }
//    public void redirectToAdminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.sendRedirect(req.getContextPath() + "/admin/login");
//    }
}
