
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/css/HomePage.css">
    <script src="/Admin/asset/js/AdminHome.js"></script>
    <link rel="stylesheet" href="/Admin/asset/fontawesome/fontawesome-free-6.4.2-web/css/all.css">
</head>
<body>
<%
    if(session.getAttribute("adminname") != null )
    {
%>
<section class="contain">
    <section class="slider-bar">
        <div class="logo-header">
            <h1 style="font-size: 40px;padding-top: 20px;"><%=session.getAttribute("adminname")%></h1>
        </div>
        <div class="list-function">
            <div class="divitem">
                <div class="index"></div>
                <div class="parent-item">
                    <div class="item-view">
                        <div class="name-item">
                            <i class="fa-solid fa-house fa-xl"></i>
                            <a href="../dashboard?action=dashboardView" target="main-content" style="margin-left: 10px">Dashboard</a>
                        </div>

                        <i class="fa-solid fa-caret-right fa-xl"></i>
                    </div>
                </div>
                <div class="sub-item">

                </div>
            </div>
            <div class="divitem">
                <div class="index"></div>
                <div class="parent-item">
                    <div class="item-view">
                        <div class="name-item">
                            <i class="fa-solid fa-calendar"></i>
                            <a href="../traveltour?action=getallTravelTour" target="main-content" style="margin-left: 10px">Lịch tour</a>
                        </div>

                        <i class="fa-solid fa-caret-right fa-xl"></i>
                    </div>
                </div>
                <div class="sub-item">
                    <div class="sub-function">
                        <i class="fa-solid fa-folder-plus" style="margin-left: 20px ;"></i>
                        <a href="../traveltour?action=ToAddTour" target="main-content" style="margin-left: 10px ;color:#fff">Tour</a>
                    </div>
                </div>
            </div>
            <div class="divitem">
                <div class="index"></div>
                <div class="parent-item">
                    <div class="item-view">
                        <div class="name-item">
                            <i class="fa-regular fa-rectangle-list"></i>
                            <a href="../tour?action=getallTour" target="main-content" style="margin-left: 10px">Danh sách tour</a>
                        </div>

                        <i class="fa-solid fa-caret-right fa-xl"></i>
                    </div>
                </div>
                <div class="sub-item">
                    <div class="sub-function">
                        <i class="fa-solid fa-folder-plus" style="margin-left: 20px ;"></i>
                        <a href="../tour?action=ToAddTour" target="main-content" style="margin-left: 10px ;color:#fff">Tour</a>
                    </div>
                    <div class="sub-function" >
                        <i class="fa-solid fa-play" style="margin-left: 20px ;"></i>
                        <a style="margin-left: 10px ;color:#fff" href="../tour?action=getallTour" target="main-content">Tất cả</a>
                    </div>
                    <div class="sub-function">
                        <i class="fa-solid fa-play" style="margin-left: 20px ;"></i>
                        <a style="margin-left: 10px ;color:#fff" href="../tour?action=getUpTour" target="main-content">Tour sắp tới</a>
                    </div>
                    <div class="sub-function">
                        <i class="fa-solid fa-play" style="margin-left: 20px ;"></i>
                        <a style="margin-left: 10px ;color:#fff" href="../tour?action=getDownTour" target="main-content">Tour đã hoàn thành</a>
                    </div>
                    <div class="sub-function">
                        <i class="fa-solid fa-play" style="margin-left: 20px ;"></i>
                        <a style="margin-left: 10px ;color:#fff" href="../tour?action=getCurrentTour" target="main-content">Tour hiện có</a>
                    </div>
                </div>
            </div>
            <div class="divitem">
                <div class="index"></div>
                <div class="parent-item">
                    <div class="item-view">
                        <div class="name-item">
                            <i class="fa-solid fa-person-chalkboard"></i>
                            <i class="fa-solid fa-earth-americas"></i>
                            <a href="../insaddress?action=insandaddress"  target="main-content" style="margin-left: 10px">Hướng dẫn viên / Địa điểm</a>
                        </div>

                        <i class="fa-solid fa-caret-right fa-xl"></i>
                    </div>
                </div>
                <div class="sub-item">

                </div>
            </div>
            <div class="divitem">
                <div class="index"></div>
                <div class="parent-item">
                    <div class="item-view">
                        <div class="name-item">
                            <i class="fa-solid fa-right-from-bracket"></i>
                            <a href="../admin-login?action=logout"  target="_self" style="margin-left: 10px">Đăng xuất</a>
                        </div>

                        <i class="fa-solid fa-caret-right fa-xl"></i>
                    </div>
                </div>
                <div class="sub-item">

                </div>
            </div>

        </div>
    </section>
    <section class="content">
        <div class="header">
            <div class="content-header">
                <div class="mini-button">
                    <i class="fa-solid fa-bars fa-2xl"></i>
                </div>
                <div class="title-content">
                    <a href="#" style="margin-left: 10px">Dashboard</a>
                </div>
            </div>

        </div>
        <iframe class="main-content" id="main-content" name="main-content" >

        </iframe>
    </section>
</section>
<%} else
{
    request.getRequestDispatcher("/Admin/AdminLogin.jsp");
}%>
</body>
</html>