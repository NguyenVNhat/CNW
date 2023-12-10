<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/css/dashboard.css">
    <script src="/Admin/asset/js/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="/Admin/asset/fontawesome/fontawesome-free-6.4.2-web/css/all.css">
</head>
<body>
<%
    Integer tourCount = (Integer) request.getAttribute("tourCount");
    Integer traveltourCount = (Integer) request.getAttribute("traveltourCount");
    Integer addressCount = (Integer) request.getAttribute("addressCount");
    Integer insCount = (Integer) request.getAttribute("insCount");
    ArrayList<Integer> revenueweek = (ArrayList<Integer>) request.getAttribute("revenueweek");
    ArrayList<Integer> getTourWeek = (ArrayList<Integer>) request.getAttribute("getTourWeek");
    Integer revenueallofweek = (Integer) request.getAttribute("revenueallofweek");
    Integer revenueallofmonth = (Integer) request.getAttribute("revenueallofmonth");
    ArrayList<Travel_Tour> getListTravel_Tour = (ArrayList<Travel_Tour>) request.getAttribute("getListTravel_Tour");
%>

<section>
    <div class="contain">
        <div class="display-info">
            <div class="item-header">
                <div class="div-item">
                    <div class="left-info">
                        <i class="fa-solid fa-layer-group"></i>
                    </div>

                    <div class="info">
                        <p><%=traveltourCount%></p>
                        <p>Tour Booked</p>
                    </div>
                </div>
            </div>
            <div class="item-header">
                <div class="left-info">
                    <i class="fa-solid fa-bars"></i>
                </div>

                <div class="info">
                    <p><p><%=tourCount%></p></p>
                    <p>Tour</p>
                </div>
            </div>
            <div class="item-header">
                <div class="left-info">
                    <i class="fa-regular fa-map"></i>
                </div>

                <div class="info">
                    <p><p><%=addressCount%></p></p>
                    <p>Address</p>
                </div>
            </div>
            <div class="item-header">
                <div class="left-info">
                    <i class="fa-solid fa-person-chalkboard"></i>
                </div>

                <div class="info">
                    <p><p><%=insCount%></p></p>
                    <p>Instructor</p>
                </div>
            </div>
        </div>
    </div>
    <div class="chartjs">
        <div class="chart_1">
            <canvas id="myChart" class="myChart"></canvas>
            <div class="total-revenue">
                <div class="title-revenue">
                    <p>Thống kê doanh thu</p>
                    <div class="revenue">
                        <p>Doanh thu trong tuần : <%=revenueallofweek%> VNĐ</p>
                        <p>Doanh thu trong tháng : <%=revenueallofmonth%> VNĐ</p>
                        <form action="../dashboard?action=revenue-week" style="margin-top: 10px" method="post">
                            <input type="date" name="time" style="height: 40px;border-radius: 10px;border: none;background-color: #c5c5c5" required/>
                            <button type="submit" style="height: 40px;border-radius: 10px;border: none;background-color: #c5c5c5" >Tra cứu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="chart_2">
            <canvas id="myChart2" class="myChart2"></canvas>
        </div>
    </div>
    <div class="traveler">
        <div class="tour">
            <%
                for (Travel_Tour travel_tour: getListTravel_Tour
                     ) {
            %>
                <div class="tour-item">
                    <div class="image-tour">
                        <img src="/Admin/asset/image/room1.30b5f04027f80ebe153b.jpg" />
                    </div>
                    <div class="tour-info">
                        <div class="address-tour divmodal">
                            <p><%=travel_tour.getTimeStart()%></p>
                            <div class="modal" style="background-color: #fff">
                            <%
                                for (String address: travel_tour.getListAddress()
                                     ) {
                            %>
                                <p style="margin-left: 20px"><%=address%></p>
                            <%}%>
                            </div>
                        </div>
                        <div class="ins-tour">
                            <div class="avata">
                                <img src="/Admin/asset/image/room1.30b5f04027f80ebe153b.jpg" />
                            </div>
                            <p >
                                <%=travel_tour.getInstructor()%>
                            </p>
                            <small><i><%=travel_tour.getPrice()%> vnđ</i></small>
                            <small><i><%=travel_tour.getToTalTime()%> ngày</i></small>

                        </div>
                    </div>
                </div>
            <%}%>
        </div>
        <div class="address-ins">
            <canvas id="myChart3" class="myChart3"></canvas>
        </div>
    </div>
    <div class="footer">
        <p>Code © Designed & Developed by DexignLab 7-12-2023</p>
    </div>
</section>
<script>
    const ctx = document.getElementById('myChart');
    const ctx2 = document.getElementById('myChart2');
    const ctx3 = document.getElementById('myChart3');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday','Sunday'],
            datasets: [{
                label: 'Doanh thu trong tuần ',
                data: [<%=revenueweek.get(0) %>, <%=revenueweek.get(1) %>, <%=revenueweek.get(2) %>, <%=revenueweek.get(3) %>, <%=revenueweek.get(4) %>, <%=revenueweek.get(5) %>, <%=revenueweek.get(6) %>],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
                ],
                borderWidth: 1,
                borderRadius:10,
                color: 'grey'
            }]
        }
    });
    new Chart(ctx2, {
        type: 'doughnut',
        data: {
            labels: ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday','Sunday'],
            datasets: [{
                label: 'Tour trong tuần ',
                data: [<%=getTourWeek.get(0)%>, <%=getTourWeek.get(1)%>, <%=getTourWeek.get(2)%>, <%=getTourWeek.get(3)%>, <%=getTourWeek.get(4)%>, <%=getTourWeek.get(5)%>,<%=getTourWeek.get(6)%>],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
                ],
                borderWidth: 1,
                borderRadius:10,
                color: 'grey'
            }]
        }
    });
    new Chart(ctx3, {
        type: 'line',
        data: {
            labels: ['Hà Tĩnh', 'Biển Hà Tĩnh', 'Sông Hà Tĩnh', 'Núi Hà Tĩnh', 'Hồ Hà Tĩnh', 'Thành phố Hà Tĩnh','Nông thôn Hà Tĩnh'],
            datasets: [{
                label: 'Địa điểm ',
                data: [65, 59, 80, 81, 56, 55, 40],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
                ],
                borderWidth: 1,
                borderRadius:10,
                color: 'grey'
            }]
        }
    });
</script>
</body>
</html>