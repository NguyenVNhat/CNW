
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/css/AdminHome.css">
    <script src="/Admin/asset/js/AdminHome.js"></script>
    <link rel="stylesheet" href="/Admin/asset/fontawesome/fontawesome-free-6.4.2-web/css/all.css">
</head>
<body>
<section class="contain">
    <section class="slider-bar">
        <div class="logo-header">
            <h1 style="font-size: 40px;padding-top: 20px;">ADMIN</h1>
        </div>
        <div class="list-function">
            <div class="divitem">
                <div class="index"></div>
                <div class="parent-item">
                    <div class="item-view">
                        <div class="name-item">
                            <i class="fa-solid fa-house fa-xl"></i>
                            <a href="#">Dashboard</a>
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
                            <i class="fa-solid fa-house fa-xl"></i>
                            <a href="../traveltour?action=getallTravelTour" target="main-content">Danh sách tour có lịch</a>
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
                            <i class="fa-solid fa-house fa-xl"></i>
                            <a href="../tour?action=getallTour" target="main-content">Danh sách tour</a>
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
                            <i class="fa-solid fa-house fa-xl"></i>
                            <p>Dashboard</p>
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
                    <p>Dashboard</p>
                </div>
            </div>

        </div>
        <iframe class="main-content" id="main-content" name="main-content">

        </iframe>
    </section>
</section>
</body>
</html>