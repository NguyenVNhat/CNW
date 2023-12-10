<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
<%@ page import="cnw.Admin.Models.Bean.Address" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Calendar" %>

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
<form action="../tour?action=deleteTour" method="post">
    <section class="contain">
        <div class="divmain">
            <div class="address_ins">
                <div class="address">

                    <div class="item_address">
                        <table class="table-list">
                            <thead class="thead">
                            <tr>

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

                                <td><%=instructor.getId()%></td>
                                <td><%=instructor.getName()%></td>
                                <td><%=instructor.getPhone()%></td>
                                <td><%=instructor.getEmail()%></td>
                                <td><%=instructor.getAge()%></td>

                            </tr>
                            <%   }
                            %>



                            </tbody>
                        </table>
                    </div>

                </div>
                <div class="address">

                    <div class="item_address">
                        <table class="table-list">
                            <thead class="thead">
                            <tr>

                                <th style="width: 5%;">ID</th>
                                <th>Address</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                ArrayList<Address> addresses = (ArrayList<Address>) request.getAttribute("detailAddress");
                                for (Address address:addresses)
                                {

                            %>
                            <tr class="tr1">

                                <td><%=address.getId()%></td>
                                <td><%=address.getAddressName()%></td>
                            </tr>
                            <%     }
                            %>


                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
            <div class="divform">
                <div class="title">
                    <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                        <p>Mã số tour</p>
                        <input type="number" name="IdTour" value="<%=tour.getId()%>" min="0" placeholder="ID tour" readonly >

                    </div>
                    <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around">
                        <p>Chi phí tour</p>
                        <input type="number"  value="<%=tour.getPrice()%>"  placeholder="giá tour" readonly>

                    </div>
                    <div class="item-input" style="height: 80px;flex-direction: column;justify-content: space-around" >
                        <p>Tổng thời gian di chuyển</p>
                        <input type="number"  onchange="calculateEndDate()" id="totalTimeInput" value="<%=tour.getToTalTime()%>" placeholder="tổng thời gian tour"  readonly>
                    </div>
                    <div class="item-input " style="flex-direction: column">
                        <p>Thời gian khởi hành</p>
                        <input class="startDate" type="date" onchange="calculateEndDate()"  readonly placeholder="thời gian khởi hành" value="<%=tour.getTimeStart()%>" id="startDate">

                    </div>
                    <%
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(tour.getTimeStart());
                        calendar.add(Calendar.DAY_OF_YEAR, tour.getToTalTime());
                        Date datedelete = calendar.getTime();
                        Date now = new Date();
                        if(tour.getTimeStart().before(now) && datedelete.after(now))
                        {
                        %>
                            <div class="buttonAdd" style="text-align: center">Can't delete now</div>

                        <%}else{
                        %>
                            <button type="submit" class="buttonAdd">Delete Tour</button>
                        <%}%>
                </div>
            </div>
        </div>

    </section>
</form>

</body>
</html>


