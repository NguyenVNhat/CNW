package cnw.utils.filter.auth;

import cnw.utils.viewdto.SummaryProfile;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(dispatcherTypes = DispatcherType.REQUEST)
public class CheckAuthorizedYetFilter extends HttpFilter {
    Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (request.getSession().getAttribute("isAuthorized")!= null && (boolean) request.getSession().getAttribute("isAuthorized")){
            logger.info("isAuthorized");
            logger.info(uri);
            logger.info(String.valueOf(request.getSession().getAttribute("summaryProfile")));
            if(uri.contains("/login"))
                redirectToHome(request, response);
            SummaryProfile summaryProfile = (SummaryProfile) request.getSession().getAttribute("summaryProfile");
            if (summaryProfile.getRole() == Role.ADMIN && uri.contains("/admin/"))
                chain.doFilter(request, response);
            else if (summaryProfile.getRole() == Role.TRAVELER && uri.contains("/traveler/"))
                chain.doFilter(request, response);
        }
        else {
            // except login and register endpoint request redirect them to login page
            logger.info("not Authorized");
            logger.info(uri);
            if (uri.contains("/register") || uri.contains("/login"))
                chain.doFilter(request, response);
            else
                redirectToLogin(request, response);
        }
    }

    private void redirectToHome(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        if (uri.contains("/admin/"))
            try {
                response.sendRedirect(request.getContextPath() + "/admin/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        else if (uri.contains("/traveler/"))
            try {
                response.sendRedirect(request.getContextPath() + "/traveler/home");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/admin/"))
            response.sendRedirect(request.getContextPath() + "/admin/login");
        else if (uri.contains("/traveler/"))
            response.sendRedirect(request.getContextPath() + "/traveler/login");
    }
}
