<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/css/detailTour.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Merriweather+Sans&display=swap" rel="stylesheet">
</head>
<body>
<%
    Tour tour = (Tour) request.getAttribute("detailTour");
%>
<section >
    <div class="contain">
        <div class="infomation-tour">
            <div class="first-item">
                <div class="top-first">
                    <div class="top-left">
                        <img src="/Admin/asset/image/room1.30b5f04027f80ebe153b.jpg" />
                    </div>
                    <div class="top-right">
                        <p style="font-size: 20px;font-weight: 600;font-family: 'Merriweather Sans', sans-serif;"> <%=tour.getName()%></p>
                        <p>ID : <%= tour.getId() %></p>
                        <blockquote> <%= tour.getDescription() %></blockquote>
                        <div class="price">
                            <p>Giá : <%= tour.getPrice() %> VNĐ</p>
                        </div>
                    </div>
                </div>
                <div class="bot-first">
                    <div class="startTime">
                        <p>Thời gian khởi hành</p>
                        <p style="font-weight: 600;"><%= tour.getTimeStart() %></p>
                    </div>
                    <div class="totalTime">
                        <p>Tổng thời gian chuyến đi</p>
                        <p style="font-weight: 600;"><%= tour.getToTalTime() %> ngày </p>
                    </div>
                </div>

            </div>
            <div class="second-item">
                <div class="instructor">
                    <p>Hướng dẫn viên : <%=tour.getInstructor()%></p>
                </div>
                <table>
                    <thead class="head-address">
                    <tr>
                        <th style="width: 10%;">-</th>
                        <th>Tên địa điểm</th>
                    </tr>
                    </thead>
                    <tbody class="body-address">
                    <%
                        for (String address: tour.getListAddress()
                        ) {
                    %>
                    <tr class="tr1">
                        <td style="width: 10%;">-</td>
                        <td><%=address%></td>
                    </tr>
                    <%} %>


                    </tbody>
                </table>
            </div>
        </div>
        <div class="image-tour">
            <div class="slideshow-container">

                <div class="mySlides fade">
                    <div class="numbertext">1 / 3</div>
                    <img src="/Admin/asset/image/poster.jpg" style="height:700px;width: 100%;">
                    <div class="text"></div>
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">2 / 3</div>
                    <img src="/Admin/asset/image/poster2.jpg" style="height:700px;width: 100%;">
                    <div class="text"></div>
                </div>

                <div class="mySlides fade">
                    <div class="numbertext">3 / 3</div>
                    <img src="/Admin/asset/image/poster3.jpg" style="height:700px;width: 100%;">
                    <div class="text"></div>
                </div>

                <a class="prev" onclick="plusSlides(-1)">❮</a>
                <a class="next" onclick="plusSlides(1)">❯</a>

            </div>
            <br>

            <div style="text-align:center">
                <span class="dot" onclick="currentSlide(1)"></span>
                <span class="dot" onclick="currentSlide(2)"></span>
                <span class="dot" onclick="currentSlide(3)"></span>
            </div>
        </div>
    </div>
</section>
<script>
    let slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("mySlides");
        let dots = document.getElementsByClassName("dot");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " active";
    }
</script>
</body>
</html>
