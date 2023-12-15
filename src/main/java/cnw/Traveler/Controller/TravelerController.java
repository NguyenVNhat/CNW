package cnw.Traveler.Controller;

import cnw.Admin.Models.Bean.Tour;
import cnw.Admin.Models.Bo.TourBo;
import cnw.Traveler.Exception.PasswordNotStrongEnough;
import cnw.Traveler.Exception.TravelerCredentialNotCorrect;
import cnw.Traveler.Exception.TravelerUsernameAlreadyExists;
import cnw.Traveler.Model.Bo.TravelerBo;
import cnw.Traveler.Model.dto.*;
import cnw.utils.servlet.FlashMap;
import cnw.utils.servlet.FlashMapManager;
import cnw.utils.viewdto.NotificationType;
import cnw.utils.viewdto.SummaryProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@WebServlet(name = "TravelerController", urlPatterns = "/traveler/*")
@MultipartConfig
public class TravelerController extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass().getName());
    TravelerBo travelerBo = new TravelerBo();
    TourBo tourBo = new TourBo();
    FlashMapManager flashMapManager = new FlashMapManager();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FlashMap flashMap = flashMapManager.retrieveFlashMapFromSession(req);
        if (flashMap == null) {
            flashMap = new FlashMap();
        }
        String uri = req.getRequestURI();
        req.setAttribute("flashMap", flashMap);

        try {
            switch (uri) {
                case "/traveler/logout" -> {
                    logger.info("logout");
                    HttpSession session = req.getSession();
                    session.invalidate();
                    redirectToLogin(req, resp);
                }
                case "/traveler/login" -> {
                    logger.info("get login page");
                    req.getRequestDispatcher("/Traveler/login.jsp").forward(req, resp);
                }
                case "/traveler/register" -> {
                    logger.info("get register page");
                    req.getRequestDispatcher("/Traveler/register.jsp").forward(req, resp);
                }
                case "/traveler/update_profile" -> {
                    logger.info("get update profile page");
                    SummaryProfile user = getCurrentUserProfile(req);
                    req.setAttribute("traveler", travelerBo.getTravelerById(user.getId()));
                    req.getRequestDispatcher("/Traveler/update_profile.jsp").forward(req, resp);
                }
                case "/traveler/change_password" -> {
                    logger.info("get change password page");
                    req.getRequestDispatcher("/Traveler/change_password.jsp").forward(req, resp);
                }
                case "/traveler/home" -> {
                    logger.info("get home page");
                    List<Tour> tours = tourBo.getAllTour();
                    int pageSize = 3;
                    int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
                    int start = (page - 1) * pageSize;
                    int end = page * pageSize;
                    int totalPage = (int) Math.ceil((double) tours.size() / pageSize);
                    List<Tour> toursPaging = tours.subList(start, Math.min(end, tours.size()));
                    List<TourDto> tourDtos = toursPaging.stream().map(t -> {
                        try {
                            return new TourDto(tourBo.getListAddress(t.getId()), t);
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();
                    Pagable pagable = new Pagable();
                    pagable.setPage(page);
                    pagable.setSize(pageSize);
                    pagable.setTotalPage(totalPage);
                    pagable.setTotalItem(tours.size());
                    pagable.setData(Collections.singletonList(tourDtos));
                    req.setAttribute("pagable", pagable);
                    req.getRequestDispatcher("/Traveler/home.jsp").forward(req, resp);
                }
                case "/traveler/tour" -> {
                    logger.info("get tour page");
                    req.getRequestDispatcher("/Traveler/tour.jsp").forward(req, resp);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FlashMap flashMap = new FlashMap();
        String uri = req.getRequestURI();
        try {
            switch (uri) {
                case "/traveler/register" -> {
                    logger.info("register");
                    RegisterTravelerDto registerDto = getRegisterTravelerFromRequest(req);
                    travelerBo.registerTraveler(registerDto);
                    flashMap.addTargetRequestParams(String.valueOf(NotificationType.INFO),"Đăng ký thành công, Bạn có thể đăng nhập ngay bây giờ");
                    flashMapManager.saveFlashMapToSession(flashMap,req,resp);
                    redirectToLogin(req,resp);
                }
                case "/traveler/update_profile" -> {
                    logger.info("update profile");
                    UpdateTravelerProfileDto updateTravelerProfileDto = getTravelerProfileFromRequest(req);
                    if (updateTravelerProfileDto.getAvatar() != null)
                        updateTravelerProfileDto.setFileName(addAvatarToImageFolder(updateTravelerProfileDto.getAvatar(), req));
                    travelerBo.updateTraveler(updateTravelerProfileDto);
                    SummaryProfile user = getCurrentUserProfile(req);
                    user.setAvatar(updateTravelerProfileDto.getFileName());
                    user.setName(updateTravelerProfileDto.getName());
                    flashMap.addTargetRequestParams(String.valueOf(NotificationType.INFO),"Cập nhật thông tin thành công");
                    flashMapManager.saveFlashMapToSession(flashMap,req,resp);
                    redirectToUpdate(req,resp);
                }
                case "/traveler/change_password" -> {
                    logger.info("change password");
                    ChangePasswordDto changePasswordDto = getChangePasswordFromRequest(req);
                    travelerBo.changePassword(changePasswordDto);
                    flashMap.addTargetRequestParams(String.valueOf(NotificationType.INFO), "Đổi mật khẩu thành công");
                    flashMapManager.saveFlashMapToSession(flashMap, req, resp);
                    redirectToChangePassword(req, resp);
                }
            }
        }
        catch (TravelerUsernameAlreadyExists e){
            RegisterTravelerDto registerDto = getRegisterTravelerFromRequest(req);
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR),"Tên đăng nhập đã tồn tại");
            flashMap.putAllTargetRequestParams(convertDtoToMap(registerDto.getClass(), registerDto));
            flashMapManager.saveFlashMapToSession(flashMap,req,resp);
            redirectToRegister(req,resp);
        }
        catch (TravelerCredentialNotCorrect e){
            Object dto = null;
            if (uri.equals("/traveler/register")){
                dto = getRegisterTravelerFromRequest(req);
            }
            else {
                dto = getChangePasswordFromRequest(req);
            }
            flashMap.putAllTargetRequestParams(convertDtoToMap(dto.getClass(), dto));
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR),"Mật khẩu cũ không đúng");
            flashMapManager.saveFlashMapToSession(flashMap,req,resp);
            redirectToChangePassword(req,resp);
        }
        catch (PasswordNotStrongEnough e){
            Object dto = null;
            if (uri.equals("/traveler/register")){
                dto = getRegisterTravelerFromRequest(req);
            }
            else {
                dto = getChangePasswordFromRequest(req);
            }
            flashMap.putAllTargetRequestParams(convertDtoToMap(dto.getClass(), dto));
            flashMap.addTargetRequestParams(String.valueOf(NotificationType.ERROR),"Mật khẩu không đủ mạnh, yêu cầu ít nhất 8 kí tự, có ít nhất 1 chữ cái in hoa, chữ cái thường, chữ số");
            flashMapManager.saveFlashMapToSession(flashMap,req,resp);
            if (uri.equals("/traveler/register")){
                redirectToRegister(req,resp);
            }
            else {
                redirectToChangePassword(req,resp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void redirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/traveler/login");
    }
    public void redirectToHome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/traveler");
    }
    public void redirectToRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/traveler/register");
    }
    public void redirectToUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/traveler/update_profile");
    }
    public void redirectToChangePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/traveler/change_password");
    }
    public UpdateTravelerProfileDto getTravelerProfileFromRequest(HttpServletRequest req) throws ServletException, IOException {
        UpdateTravelerProfileDto updateTravelerProfileDto = new UpdateTravelerProfileDto();
        SummaryProfile user = getCurrentUserProfile(req);
        updateTravelerProfileDto.setId(user.getId());
        updateTravelerProfileDto.setAddress(req.getParameter("address"));
        if (req.getParameter("dayBorn") == null || req.getParameter("dayBorn").equals(""))
            updateTravelerProfileDto.setDayBorn(null);
        else
            updateTravelerProfileDto.setDayBorn(Date.valueOf(req.getParameter("dayBorn")));
        updateTravelerProfileDto.setEmail(req.getParameter("email"));
        updateTravelerProfileDto.setName(req.getParameter("name"));
        updateTravelerProfileDto.setPhone(req.getParameter("phone"));
        Part avatar = req.getPart("avatar");
        if (avatar.getSize() == 0)
            updateTravelerProfileDto.setAvatar(null);
        else
            updateTravelerProfileDto.setAvatar(avatar);
        return updateTravelerProfileDto;
    }
    public RegisterTravelerDto getRegisterTravelerFromRequest(HttpServletRequest req){
        RegisterTravelerDto registerTravelerDto = new RegisterTravelerDto();
        registerTravelerDto.setName(req.getParameter("name"));
        registerTravelerDto.setEmail(req.getParameter("email"));
        registerTravelerDto.setUsername(req.getParameter("username"));
        registerTravelerDto.setPassword(req.getParameter("password"));
        return registerTravelerDto;
    }
    public ChangePasswordDto getChangePasswordFromRequest(HttpServletRequest req){
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        SummaryProfile user = getCurrentUserProfile(req);
        changePasswordDto.setId(user.getId());
        changePasswordDto.setOldPassword(req.getParameter("oldPassword"));
        changePasswordDto.setNewPassword(req.getParameter("newPassword"));
        return changePasswordDto;
    }
    private SummaryProfile getCurrentUserProfile(HttpServletRequest req){
        HttpSession session = req.getSession();
        return (SummaryProfile)session.getAttribute("summaryProfile");
    }
    private Map<String, String> convertDtoToMap(Class<?> clazz, Object obj){
        Map<String,String> map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            try {
                map.put(field.getName(),String.valueOf(field.get(obj)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    public String addAvatarToImageFolder(Part avatarFilePart, HttpServletRequest req) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + getExtension(avatarFilePart.getSubmittedFileName());
        String UPLOAD_DIR = getCurrentRootPath(req);
        String targetFilePath = UPLOAD_DIR + "images\\avatars\\" + fileName;
        String filePath = UPLOAD_DIR.replace("\\target","").replace("\\BTN_CNW","")+"src\\main\\webapp\\images\\avatars\\" + fileName;

        // Create the directory if it doesn't exist
        File targetDir = new File(getCurrentRootPath(req) + "images\\avatars\\");
        if (!targetDir.exists()) {
            if (!targetDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + UPLOAD_DIR);
            }
        }
        File fileDir = new File(UPLOAD_DIR.replace("\\target","").replace("\\BTN_CNW","")+"src\\main\\webapp\\images\\avatars\\");
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + UPLOAD_DIR);
            }
        }

        // Write the file to the specified path
        try (InputStream fileContent = avatarFilePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            int read;
            final byte[] buffer = new byte[1024];
            while ((read = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            // Handle any exceptions during file write
            // For example, you could log the error:
            e.printStackTrace();
            throw new IOException("Failed to upload file: " + e.getMessage());
        }
        //write to target folder
        try (InputStream fileContent = avatarFilePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(targetFilePath)) {

            int read;
            final byte[] buffer = new byte[1024];
            while ((read = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            // Handle any exceptions during file write
            // For example, you could log the error:
            e.printStackTrace();
            throw new IOException("Failed to upload file: " + e.getMessage());
        }

        return fileName;
    }
    public String getExtension(String fileName){
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
    public String getCurrentRootPath(HttpServletRequest req){
         return req.getServletContext().getRealPath("/");
    }
}
