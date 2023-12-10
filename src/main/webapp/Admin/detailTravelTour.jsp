<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Traveler" %>
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
    Travel_Tour tour =(Travel_Tour) request.getAttribute("tours");

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
                        <p style="font-size: 50px;font-weight: 600;font-family: 'Merriweather Sans', sans-serif;">TOUR <%= tour.getIdTour() %></p>
                        <p>ID : <%= tour.getIdTour() %></p>
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
                    <p>Hướng dẫn viên :<%= tour.getInstructor() %> </p>
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
        <div class="image-tour" style="width: 50%">
            <table style="width: 100%">
                <thead class="head-address">
                <tr>
                    <th>Id</th>
                    <th>Họ tên</th>
                    <th>Địa chỉ</th>
                    <th>Email</th>
                    <th>SĐT</th>
                </tr>
                </thead>
                <tbody class="body-address">
                <%
                    for (Traveler traveler: tour.getListTraveler()
                    ) {
                %>
                <tr class="tr1">
                    <td><%=traveler.getId()%></td>
                    <td><%=traveler.getName()%></td>
                    <td><%=traveler.getAddress()%></td>
                    <td><%=traveler.getEmail()%></td>
                    <td><%=traveler.getPhone()%></td>

                </tr>
                <%} %>


                </tbody>
            </table>

        </div>
    </div>
</section>

</body>
</html>

