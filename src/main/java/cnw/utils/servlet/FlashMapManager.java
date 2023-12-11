package cnw.utils.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FlashMapManager {
    public void saveFlashMapToSession(FlashMap flashMap, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("flashMap", flashMap);
    }
    public FlashMap retrieveFlashMapFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        FlashMap flashMap = (FlashMap) session.getAttribute("flashMap");
        session.removeAttribute("flashMap");
        return flashMap;
    }
}
