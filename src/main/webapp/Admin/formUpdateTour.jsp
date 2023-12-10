<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
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
<%
    Tour tour = (Tour) request.getAttribute("detailTour");
    ArrayList<Instructor> instructorALl  = (ArrayList<Instructor>)  request.getAttribute("instructorALl");
    ArrayList<Address> addressesAll  = (ArrayList<Address>)  request.getAttribute("addressesAll");
 %>
<form action="../tour?action=updateTour" method="post">
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
                                for (Instructor instructor:instructorALl
                                ) {
                                    Boolean key =false;
                                    for (Instructor instructorsub: instructors)
                                    {
                                        if (instructor.getName().equals(instructorsub.getName()))
                                        {
                                            key = true;
                                            break;
                                        }
                                    }
                                    if(key)
                                    {
                            %>
                                    <tr class="tr1">
                                        <td style="width: 5%;"><input type="radio" name="IdInstructor" value="<%=instructor.getId()%>" checked/> </td>
                                        <td><%=instructor.getId()%></td>
                                        <td><%=instructor.getName()%></td>
                                        <td><%=instructor.getPhone()%></td>
                                        <td><%=instructor.getEmail()%></td>
                                        <td><%=instructor.getAge()%></td>

                                    </tr>
                            <%      }
                                    else {
                            %>
                            <tr class="tr1">
                                <td style="width: 5%;"><input type="radio" name="IdInstructor" value="<%=instructor.getId()%>" /> </td>
                                <td><%=instructor.getId()%></td>
                                <td><%=instructor.getName()%></td>
                                <td><%=instructor.getPhone()%></td>
                                <td><%=instructor.getEmail()%></td>
                                <td><%=instructor.getAge()%></td>

                            </tr>
                            <%}}%>



                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="address">

                    <div class="item_address">
                        <table class="table-list">
                            <thead class="thead">
                            <tr>
                                <th style="width: 5%;"></th>
                                <th style="width: 5%;">ID</th>
                                <th>Address</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                ArrayList<Address> addresses = (ArrayList<Address>) request.getAttribute("detailAddress");
                                for (Address address:addressesAll)
                                {
                                    Boolean key =false;
                                    for (Address addresschecked: addresses)
                                    {
                                        if (address.getAddressName().equals(addresschecked.getAddressName()))
                                        {
                                            key = true;
                                            break;
                                        }
                                    }
                                    if(key)
                                    {
                            %>
                                    <tr class="tr1">
                                        <td style="width: 5%;"><input type="checkbox" name="IdAddress" value="<%=address.getId()%>" checked /> </td>
                                        <td><%=address.getId()%></td>
                                        <td><%=address.getAddressName()%></td>
                                    </tr>
                            <%      }
                                    else
                                    {
                            %>
                                    <tr class="tr1">
                                        <td style="width: 5%;"><input type="checkbox" name="IdAddress" value="<%=address.getId()%>"  /> </td>
                                        <td><%=address.getId()%></td>
                                        <td><%=address.getAddressName()%></td>
                                    </tr>
                            <%      }
                            }%>


                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <div class="divform">
                <div class="title">
                    <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                        <p>Mã số tour</p>
                        <input type="number" name="Id" value="<%=tour.getId()%>" min="0" placeholder="ID tour" readonly >

                    </div>
                    <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                        <p>Chi phí tour</p>
                        <input type="number" name="Price" value="<%=tour.getPrice()%>"  placeholder="giá tour">

                    </div>
                    <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around" >
                        <p>Tổng thời gian di chuyển</p>
                        <input type="number" name="TotalTime" onchange="calculateEndDate()" id="totalTimeInput" value="<%=tour.getToTalTime()%>" placeholder="tổng thời gian tour" >
                    </div>
                    <div class="item-input " style="flex-direction: column">
                        <p>Thời gian khởi hành</p>
                        <input class="startDate" type="date" onchange="calculateEndDate()" name="TimeStart" placeholder="thời gian khởi hành" value="<%=tour.getTimeStart()%>" id="startDate">
                        <p>Thời gian kết thúc</p>
                        <input class="endDate" type="date"  placeholder="thời gian khởi hành" value="" id="endDate" readonly>
                    </div>
                    <button type="submit" class="buttonAdd">Update Tour</button>
                </div>
            </div>
        </div>

    </section>
</form>
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
</body>
</html>


