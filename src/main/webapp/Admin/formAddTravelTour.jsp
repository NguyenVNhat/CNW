<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Address" %>
<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page import="cnw.Admin.Models.Bean.Traveler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        section{
            width: 100%;
            height: 735px;
            display: flex;
            justify-content: center;
            align-content: center;
        }
        .contain{
            width: 95%;
            height: 96%;
            display: flex;
            justify-content: space-between;
        }
        .address{
            width: 30%;
            height: 100%;
        }
        .instructor{
            width: 68%;
            height: 100%;
        }
        .table-address{
            width: 100%;
        }
        thead{
            height: 60px;
            background-color: rgb(75, 165, 244);
        }
        td{
            text-align: center;
        }
        .tr1{
            height: 40px;
            background-color: rgb(204, 228, 249);
        }
        input{
            width: 150px;
            height: 40px;
            background-color: lightblue;
            border: none;
            border-radius: 4px;
            text-align: center;
            margin-bottom: 10px;
        }
        input:focus{
            border: 0.5px solid;
            border-color: lightgreen;
            outline: none;
        }
        a{
            text-decoration: none;
            color: #000;
        }
        .modal{
            position: absolute;
            width: 120%;
            text-align: start;
            min-height: 100px;
            z-index: 1;
            display: none;
            background-color: rgb(255, 255, 255);
            bottom: -200%;
            box-shadow: 0 0 10px #000;
            border-radius: 10px;
            animation-name: modalAni;
            animation-duration: 0.5s;
        }
        @keyframes modalAni {
            0%{
                bottom:  -180%;
            }
            100%{
                bottom: -200%;
            }
        }

        .button_modal{
            width: 50%;
            height: 40px;
            border-radius: 10px;
            border: none;
            box-shadow: 0 0 5px #000;
        }
    </style>
</head>
<body>
<%
    ArrayList<Tour> tours = (ArrayList<Tour>) request.getAttribute("tours");
    ArrayList<Traveler> travelers = (ArrayList<Traveler>) request.getAttribute("travelers");
%>
<section>
    <form action="../traveltour?action=AddTravelTour" method="post" style="width: 100%;height: 100%" onsubmit="return validateForm()" >
        <button type="submit" id="addButton" style="width: 100px;height: 30px;border: none;background-color: lightgreen">Thêm</button>
        <div class="contain">
        <div class="address">
            <table class="table-address">
                <thead>
                <tr>
                    <th style="width: 30%"><input style="width: 15px;height: 15px" type="checkbox" id="selectAll"></th>
                    <th style="width: 70%">  Name</th>

                </tr>
                </thead>
                <tbody>
                <%
                    for (Traveler traveler: travelers
                    ) {
                %>
                <tr class="tr1">
                    <td><input style="width: 15px;height: 15px" type="checkbox" name="IdTraveler" class="rowCheckbox" id="rowCheckbox" value="<%=traveler.getId()%>" /></td>
                    <td><%=traveler.getName() %></td>
                </tr>
                <%}%>

                </tbody>
            </table>
        </div>
        <div class="instructor">
            <table class="table-address">
                <thead>
                <tr>
                    <th style="width: 10%">#</th>
                    <th>Price</th>
                    <th>Start time</th>
                    <th>Total time</th>
                    <th>Address</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Tour tour: tours
                    ) {
                %>
                <tr class="tr1">
                    <td><input style="width: 15px;height: 15px" type="radio" name="IdTour" value="<%=tour.getId()%>" checked></td>
                    <td><%=tour.getPrice() %></td>
                    <td><%=tour.getTimeStart() %></td>
                    <td><%=tour.getToTalTime() %> ngày</td>
                    <td style="position: relative;" class="divmodal">
                        <button class="button_modal" type="button">
                            View
                        </button>
                        <div class="modal" >
                            <%
                                if(tour.getListAddress().isEmpty())
                                {
                            %>
                            <p style="margin-left: 10px;margin-top: 20px">Chưa chọn địa điểm cho tour này</p>
                            <%}
                            else {
                                Integer index = 1;
                                for (String address: tour.getListAddress()
                                ) {
                            %>
                            <p style="margin-left: 10px;margin-top: 10px">Địa điểm <%=index%> : <%=address%></p>
                            <% index++;}} %>
                        </div>
                    </td>

                </tr>
                <%}%>

                </tbody>
            </table>
        </div>
    </div>

    </form>
</section>

<script>
    const buttonmodal = document.querySelectorAll('.divmodal');
    buttonmodal.forEach(function(button){
        const modal = button.querySelector('.modal');
        button.addEventListener('mouseover',function() {
            modal.style.display = 'block';
        })
        button.addEventListener('mouseout',function() {
            modal.style.display = 'none';
        })
    })
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
    document.addEventListener('change', function(event) {
        if (event.target.matches('input[type="checkbox"].rowCheckbox')) {
            var checkboxes = document.querySelectorAll('input[type="checkbox"].rowCheckbox');
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
</script>

</body>
</html>
