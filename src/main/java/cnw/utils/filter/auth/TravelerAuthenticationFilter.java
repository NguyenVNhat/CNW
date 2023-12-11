package cnw.utils.filter.auth;

import cnw.Traveler.Exception.TravelerNotFound;
import cnw.Traveler.Model.Bean.Traveler;
import cnw.Traveler.Model.Bo.TravelerBo;
import cnw.utils.servlet.FlashMap;
import cnw.utils.servlet.FlashMapManager;
import cnw.utils.viewdto.NotificationType;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebFilter(dispatcherTypes = DispatcherType.REQUEST)
public class TravelerAuthenticationFilter extends HttpFilter {
    FlashMapManager flashMapManager = new FlashMapManager();
    TravelerBo travelerBo = new TravelerBo();

    Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        if (req.getMethod().equals("GET") && uri.equals("/traveler/login")){
            logger.info("get login page");
            filterChain.doFilter(req, resp);
            return;
        }
        if(uri.equals("/traveler/register")){
            logger.info("get register page");
            filterChain.doFilter(req, resp);
            return;
        }
        FlashMap flashMap = new FlashMap();
        UserCredential reqUserCredential = getUserCredentialFromReq(req);
        UserCredential dbUserCredential = null;

        try {
            dbUserCredential = getUserCredentialFromDb(reqUserCredential.getUsername());
        } catch (TravelerNotFound e) {
            addInputCredentialToFlashMap(flashMap, reqUserCredential);
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR), "Thông tin đăng nhập không chính xác");
            flashMapManager.saveFlashMapToSession(flashMap, req, resp);
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            redirectLogin(req, resp);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (reqUserCredential.getPassword().equals(dbUserCredential.getPassword())) {
            req.setAttribute("userCredential", reqUserCredential);
            filterChain.doFilter(req, resp);
        } else {
            addInputCredentialToFlashMap(flashMap, reqUserCredential);
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR), "Thông tin đăng nhập không chính xác");
            flashMapManager.saveFlashMapToSession(flashMap, req, resp);
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            redirectLogin(req, resp);
        }

    }
    public UserCredential getUserCredentialFromReq(HttpServletRequest req){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return new UserCredential(username, password, Role.TRAVELER);
    }
    public UserCredential getUserCredentialFromDb(String username) throws TravelerNotFound, SQLException, ClassNotFoundException {
        Traveler traveler = travelerBo.getTravelerByUsername(username);
        return new UserCredential(username, traveler.getPassword(), Role.TRAVELER);
    }
    public void redirectLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect( "/traveler/login");
    }
    public void addInputCredentialToFlashMap(FlashMap flashMap, UserCredential userCredential){
        flashMap.addTargetRequestParams("username", userCredential.getUsername());
        flashMap.addTargetRequestParams("password", userCredential.getPassword());
    }

}
