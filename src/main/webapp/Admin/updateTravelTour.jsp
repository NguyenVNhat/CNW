<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Traveler" %>
<%@ page import="java.util.Objects" %>
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
    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgb(0,0,0);
            background-color: rgba(0,0,0,0.4);
        }
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<%
    Travel_Tour tour =(Travel_Tour) request.getAttribute("tours");
    ArrayList<Traveler> travelers = (ArrayList<Traveler>) request.getAttribute("travelers");
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
            <button id="myBtn" type="button" style="width: 100px;height: 30px;border: none;background-color: lightgreen">Thêm</button>

            <div id="myModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <form action="../traveltour?action=addTravelInTour" method="post" target="_self" onsubmit="return validateFormAdd()">
                        <input type="hidden" name="IdTour" value="<%=tour.getIdTour()%>" />
                        <table style="width: 100%">
                            <thead class="head-address">
                            <tr>
                                <th><input type="checkbox" id="selectAllTraveler"></th>
                                <th>Họ tên</th>
                                <th>Địa chỉ</th>
                                <th>Email</th>
                                <th>SĐT</th>
                            </tr>
                            </thead>
                            <tbody class="body-address">
                            <%
                                for (Traveler traveler: travelers
                                ) {
                                    Boolean key = true;
                                    for (Traveler traveler1: tour.getListTraveler()
                                    ) {
                                        if (Objects.equals(traveler1.getId(), traveler.getId()))
                                        {
                                            key = false;
                                            break;
                                        }
                                    }
                                    if(key)
                                    {
                            %>
                            <tr class="tr1">
                                <td><input type="checkbox" name="IdTravel1" class="rowCheckboxTraveler" value="<%=traveler.getId()%>"></td>
                                <td><%=traveler.getName()%></td>
                                <td><%=traveler.getAddress()%></td>
                                <td><%=traveler.getEmail()%></td>
                                <td><%=traveler.getPhone()%></td>

                            </tr>
                            <%}} %>
                            </tbody>
                        </table>
                        <button type="submit" style="width: 100px;height: 30px;border: none;background-color: lightgreen;margin-top: 20px" id="addButton">Thêm</button>
                    </form>
                </div>

            </div>
            <form style="width: 100%;height: 100%" action="../traveltour?action=deleteTravelInTour" method="post" onsubmit="return validateForm()">
                <input type="hidden" name="IdTour" value="<%=tour.getIdTour()%>" />
            <table style="width: 100%">
                <thead class="head-address">
                <tr>
                    <th><input type="checkbox" id="selectAll"></th>
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
                    <td><input type="checkbox" name="IdTravel" class="rowCheckbox" value="<%=traveler.getId()%>"></td>
                    <td><%=traveler.getName()%></td>
                    <td><%=traveler.getAddress()%></td>
                    <td><%=traveler.getEmail()%></td>
                    <td><%=traveler.getPhone()%></td>

                </tr>
                <%} %>
                </tbody>
            </table>
                <button type="submit" id="deleteButton" style="width: 100px;height: 30px;border: none;border-radius: 10px">Xóa</button>
            </form>


        </div>
    </div>
</section>
<script>
    const selectAllTraveler = document.getElementById('selectAllTraveler');
    const rowCheckboxTraveler = document.querySelectorAll('.rowCheckboxTraveler');
    selectAllTraveler.addEventListener('change', function() {
        rowCheckboxTraveler.forEach(function(checkbox) {
            checkbox.checked = selectAllTraveler.checked;
        });
    });

</script>
<script>
    const selectAllCheckbox = document.getElementById('selectAll');
    const rowCheckboxes = document.querySelectorAll('.rowCheckbox');
    selectAllCheckbox.addEventListener('change', function() {
        rowCheckboxes.forEach(function(checkbox) {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });

</script>
<script>
    var modal = document.getElementById("myModal");
    var btn = document.getElementById("myBtn");
    var span = document.getElementsByClassName("close")[0];
    btn.onclick = function() {
        modal.style.display = "block";
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

<script>
    function validateForm() {
        var checkboxes = document.querySelectorAll('input[type="checkbox"].rowCheckbox');
        var checked = false;
        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                checked = true;
            }
        });
        if (!checked) {
            alert("Vui lòng chọn ít nhất một ô.");
            return false;
        }
        return true;
    }
    function validateFormAdd() {
        var checkboxes = document.querySelectorAll('input[type="checkbox"].rowCheckboxTraveler');
        var checked = false;
        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                checked = true;
            }
        });
        if (!checked) {
            alert("Vui lòng chọn ít nhất một ô.");
            return false;
        }
        return true;
    }
    document.addEventListener('change', function(event) {
        if (event.target.matches('input[type="checkbox"].rowCheckbox')) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"].rowCheckboxTraveler');
            var deleteButton = document.getElementById('addButton');
            var checked = false;
            checkboxes.forEach(function(checkbox) {
                if (checkbox.checked) {
                    checked = true;
                }
            });
            if (checked) {
                deleteButton.disabled = false;
            } else {
                deleteButton.disabled = true;
            }
        }
    });
    document.addEventListener('change', function(event) {
        if (event.target.matches('input[type="checkbox"].rowCheckbox')) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"].rowCheckbox');
            var deleteButton = document.getElementById('deleteButton');
            var checked = false;
            checkboxes.forEach(function(checkbox) {
                if (checkbox.checked) {
                    checked = true;
                }
            });
            if (checked) {
                deleteButton.disabled = false;
            } else {
                deleteButton.disabled = true;
            }
        }
    });
</script>

</body>
</html>

