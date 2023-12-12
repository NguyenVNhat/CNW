package cnw.utils.filter.auth;

import cnw.Admin.Models.AdminNotFoundException;
import cnw.Admin.Models.Bo.AdminBo;
import cnw.utils.servlet.FlashMap;
import cnw.utils.servlet.FlashMapManager;
import cnw.utils.viewdto.NotificationType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebFilter(dispatcherTypes = DispatcherType.REQUEST)
public class AdminAuthenticationFilter implements Filter {
    FlashMapManager flashMapManager = new FlashMapManager();
    AdminBo adminBo = new AdminBo();

    public UserCredential getUserCredentialFromReq(HttpServletRequest req){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return new UserCredential(username, password, Role.ADMIN);
    }
    public UserCredential getUserCredentialFromDb(String username) throws SQLException, AdminNotFoundException, ClassNotFoundException {
        adminBo = new AdminBo();
        return new UserCredential(username, adminBo.getAdminByUsername(username).getPassword(), Role.ADMIN);
    }
    public void redirectLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/admin/login");
    }
    public void addInputCredentialToFlashMap(FlashMap flashMap, UserCredential userCredential){
        flashMap.addTargetRequestParams("username", userCredential.getUsername());
        flashMap.addTargetRequestParams("password", userCredential.getPassword());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if(req.getMethod().equals("GET")){
            filterChain.doFilter(req, resp);
            return;
        }
        FlashMap flashMap = new FlashMap();
        UserCredential reqUserCredential = getUserCredentialFromReq(req);
        UserCredential dbUserCredential = null;

        try {
            dbUserCredential = getUserCredentialFromDb(reqUserCredential.getUsername());
        } catch (AdminNotFoundException e) {
            addInputCredentialToFlashMap(flashMap, reqUserCredential);
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR), "Thông tin đăng nhập không chính xác");
            flashMapManager.saveFlashMapToSession(flashMap, req, resp);
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            redirectLogin(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (reqUserCredential.getPassword().equals(dbUserCredential.getPassword())) {
            req.setAttribute("userCredential", dbUserCredential);
            filterChain.doFilter(req, resp);
        } else {
            addInputCredentialToFlashMap(flashMap, reqUserCredential);
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR), "Thông tin đăng nhập không chính xác");
            flashMapManager.saveFlashMapToSession(flashMap, req, resp);
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            redirectLogin(req, resp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
