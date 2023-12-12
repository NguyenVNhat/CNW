<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Address" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/css/AddTour.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap" rel="stylesheet">
    <script src="/Admin/asset/js/formAdd.js"></script>
</head>
<body>
<form action="../tour?action=addTour" method="post" onsubmit="return validateForm()">
<section class="contain">
    <div class="divmain">
        <div class="address_ins">
            <div class="address">

                <div class="item_address">
                    <table class="table-list">
                        <thead class="thead">
                        <tr>
                            <th style="width: 5%;"></th>
                            <th>ID</th>
                            <th>Instructor</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Age</th>

                        </tr>
                        </thead>
                        <tbody>
                        <%

                            ArrayList<Instructor> instructors = (ArrayList<Instructor>) request.getAttribute("detailIns");
                            for (Instructor instructor:instructors
                            ) {
                        %>
                            <tr class="tr1">
                                <td style="width: 5%;"><input type="radio" checked name="IdInstructor" value="<%=instructor.getId()%>"/> </td>
                                <td><%=instructor.getId()%></td>
                                <td><%=instructor.getName()%></td>
                                <td><%=instructor.getPhone()%></td>
                                <td><%=instructor.getEmail()%></td>
                                <td><%=instructor.getAge()%></td>

                            </tr>
                        <%}%>



                        </tbody>
                    </table>
                </div>

            </div>
            <div class="address">

                <div class="item_address">
                    <table class="table-list">
                        <thead class="thead">
                        <tr>
                            <th style="width: 5%;" ><input type="checkbox" id="selectAll"></th>
                            <th style="width: 5%;">ID</th>
                            <th>Address</th>


                        </tr>
                        </thead>
                        <tbody>
                        <%

                            ArrayList<Address> addresses = (ArrayList<Address>) request.getAttribute("detailAddress");
                            for (Address address:addresses
                            ) {
                        %>
                        <tr class="tr1">
                            <td style="width: 5%;"><input type="checkbox" class="rowCheckbox"  name="IdAddress" value="<%=address.getId()%>"/> </td>
                            <td><%=address.getId()%></td>
                            <td><%=address.getAddressName()%></td>
                        </tr>
                        <%}%>


                        </tbody>
                    </table>
                </div>

            </div>
        </div>
        <div class="divform">
            <div class="title">
                <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                    <p>Mã số tour</p>
                    <input type="number" name="Id" value="0" min="0" placeholder="ID tour" >

                </div>
                <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                    <p>Tên tour</p>
                    <input type="text" name="Name" value="----" placeholder="Name tour" >
                </div>
                <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                    <p>Chi phí tour</p>
                    <input type="number" name="Price" value="0"  placeholder="giá tour">

                </div>
                <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around" >
                    <p>Tổng thời gian di chuyển</p>
                    <input type="number" name="TotalTime" onchange="calculateEndDate()" id="totalTimeInput" value="0" placeholder="tổng thời gian tour" >
                </div>
                <div class="item-input " style="flex-direction: column">
                    <p>Thời gian khởi hành</p>
                    <input class="startDate" type="date" onchange="calculateEndDate()" name="TimeStart" placeholder="thời gian khởi hành" value="" id="startDate">
                    <p>Thời gian kết thúc</p>
                    <input class="endDate" type="date"  placeholder="thời gian khởi hành" value="" id="endDate" readonly>
                </div>
                <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around" >
                    <p> Mô tả </p>
                    <input type="text" name="Description"   value="--" placeholder="mô tả" >
                </div>
                <%
                    String error = (String )request.getAttribute("error");
                    if (error ==null)
                    {

                    }
                    else{
                %>
                <p><%=error%></p>
                <%}%>

            <button type="submit" class="buttonAdd">Thêm Tour</button>
            </div>
        </div>
    </div>

</section>
</form>
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
    var today = new Date();
    var formattedDate = today.toISOString().substr(0, 10);
    document.getElementById("startDate").value = formattedDate;
    document.getElementById("endDate").value = formattedDate;


    document.getElementById('totalTimeInput').addEventListener('input', function() {
        calculateEndDate();
    });

    document.getElementById('startDate').addEventListener('input', function() {
        calculateEndDate();
    });

    function calculateEndDate() {
        var startDateValue = document.getElementById('startDate').value;
        var totalTimeValue = parseInt(document.getElementById('totalTimeInput').value);

        if (startDateValue !== '' && !isNaN(totalTimeValue)) {
            var startDate = new Date(startDateValue);
            var endDate = new Date(startDate.getTime() + totalTimeValue * 24 * 60 * 60 * 1000);
            var formattedEndDate = endDate.toISOString().substr(0, 10);

            document.getElementById('endDate').value = formattedEndDate;
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

