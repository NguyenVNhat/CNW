<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
<%@ page import="cnw.Admin.Models.Bean.Traveler" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/fontawesome/fontawesome-free-6.4.2-web/css/all.css">
    <link rel="stylesheet" href="/Admin/asset/css/ListTravel_Tour.css">
    <script src="/Admin/asset/js/ListTravel_Tour.js"></script>
</head>
<body>

<section class="contain">
    <section class="header">
        <div class="taskbar">
            <%
                String pageId = (String) request.getAttribute("pageId");

                if(pageId.equals("1"))
                {
            %>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;" >
                <a style="margin-right: 5px" href="../traveltour?action=getallTravelTour" target="_self">Tất cả</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../traveltour?action=getUpTravelTour" target="_self">Sắp tới</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../traveltour?action=getDownTravelTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../traveltour?action=getCurrentTravelTour" target="_self">Hiện có</a>
            </div>
            <%} else if (pageId.equals("2")) {

            %>
            <div class="item "  >
                <a style="margin-right: 5px" href="../traveltour?action=getallTravelTour" target="_self">Tất cả</a>
            </div>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../traveltour?action=getUpTravelTour" target="_self">Sắp tới</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../traveltour?action=getDownTravelTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../traveltour?action=getCurrentTravelTour" target="_self">Hiện có</a>
            </div>
            <%} else if (pageId.equals("3")) {
            %>
            <div class="item "  >
                <a style="margin-right: 5px" href="../traveltour?action=getallTravelTour" target="_self">Tất cả</a>
            </div>
            <div class="item ">
                <a style="margin-right: 5px" href="../traveltour?action=getUpTravelTour" target="_self">Sắp tới</a>
            </div>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../traveltour?action=getDownTravelTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../traveltour?action=getCurrentTravelTour" target="_self">Hiện có</a>
            </div>
            <%} else if (pageId.equals("4")) {
            %>
            <div class="item "  >
                <a style="margin-right: 5px" href="../traveltour?action=getallTravelTour" target="_self">Tất cả</a>
            </div>
            <div class="item ">
                <a style="margin-right: 5px" href="../traveltour?action=getUpTravelTour" target="_self">Sắp tới</a>
            </div>
            <div class="item " >
                <a style="margin-right: 5px" href="../traveltour?action=getDownTravelTour" target="_self">Đã qua</a>
            </div>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../traveltour?action=getCurrentTravelTour" target="_self">Hiện có</a>
            </div>
            <%}%>

        </div>
        <div class="findTour">
            <div class="divForm">
                <form action="../traveltour?action=findTour&typeSearch=<%=pageId%>" method="post" style="display: flex;width: 100%;height: 100%;">
                    <input type="text" name="textsearch" class="textsearch" />
                    <button type="submit" class="buttonSubmit">
                        OK
                    </button>
                </form>
            </div>
        </div>
        <div class="findByTime">
            <div class="border">
                <form class="formTime" action="../traveltour?action=findTourDay&pageId=<%=pageId%>" method="post">
                    <select  name="textsearch" class="textsearch_1">
                        <option value="1">Today</option>
                        <option value="2">Yesterday</option>
                        <option value="3">Last 7 days</option>
                        <option value="4">This month</option>
                        <option value="5">Last month</option>
                    </select>
                    <button type="submit" style="width: 17%;border: 0.5px solid; border-left: none;border-radius: 0 10px 10px 0;">
                        ok
                    </button>
                </form>
            </div>
        </div>
    </section>
    <div class="iframe" id="mainFrame" >
        <form action="../traveltour?action=deleteAll" method="post" onsubmit="return validateForm()">
        <table class="table-list">
            <thead class="thead">
            <tr>
                <th style="width: 5%;"><input type="checkbox" id="selectAll"/></th>
                <th>TimeStart</th>
                <th>Price</th>
                <th>Address</th>
                <th>ToTalTime</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Travel_Tour> tours = (ArrayList<Travel_Tour>) request.getAttribute("tours");
                for (Travel_Tour item: tours
                ) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(item.getTimeStart());
                    calendar.add(Calendar.DAY_OF_YEAR, item.getToTalTime());
                    Date datedelete = calendar.getTime();
                    Date now = new Date();
                    if(item.getTimeStart().before(now) && datedelete.after(now))
                    {
            %>
            <tr class="tr1">
                <td>-</td>
                <td><%=item.getTimeStart() %></td>
                <td><%=item.getPrice() %></td>
                <td style="position: relative;" class="divmodal">
                    <button class="button_modal" type="button" >
                        View
                    </button>
                    <div class="modal" >
                        <%
                            if(item.getListAddress().isEmpty())
                            {
                        %>
                        <p style="margin-left: 10px;margin-top: 20px">Chưa chọn địa điểm cho tour này</p>
                        <%}
                        else {
                            Integer index = 1;
                            for (String address: item.getListAddress()
                            ) {
                        %>
                        <p style="margin-left: 10px;margin-top: 10px">Địa điểm <%=index%> : <%=address%></p>
                        <% index++;}} %>
                    </div>
                </td>
                <td><%=item.getToTalTime() %> ngày</td>
                <td>
                    <a style="margin-right: 10px;" href="../traveltour?action=getdetailTour&Id=<%= item.getIdTour()%>" target="_self"><i class="fa-regular fa-eye"></i></a>
                    <a style="margin-right: 10px;" href="../traveltour?action=ToupdateTour&Id=<%= item.getIdTour()%>"><i class="fa-regular fa-pen-to-square"></i></a>

                </td>
            </tr>
            <%}else{
            %>
            <tr class="tr1">
                <td><input type="checkbox" class="rowCheckbox" name="IdTour" value="<%=item.getIdTour()%>"/></td>
                <td><%=item.getTimeStart() %></td>
                <td><%=item.getPrice() %></td>
                <td style="position: relative;" class="divmodal">
                    <button class="button_modal" >
                        View
                    </button>
                    <div class="modal" >
                        <%
                            if(item.getListAddress().isEmpty())
                            {
                        %>
                        <p style="margin-left: 10px;margin-top: 20px">Chưa chọn địa điểm cho tour này</p>
                        <%}
                        else {
                            Integer index = 1;
                            for (String address: item.getListAddress()
                            ) {
                        %>
                        <p style="margin-left: 10px;margin-top: 10px">Địa điểm <%=index%> : <%=address%></p>
                        <% index++;}} %>
                    </div>
                </td>
                <td><%=item.getToTalTime() %> ngày</td>
                <td>
                    <a style="margin-right: 10px;" href="../traveltour?action=getdetailTour&Id=<%= item.getIdTour()%>" target="_self"><i class="fa-regular fa-eye"></i></a>
                    <a style="margin-right: 10px;" href="../traveltour?action=ToupdateTour&Id=<%= item.getIdTour()%>"><i class="fa-regular fa-pen-to-square"></i></a>

                </td>
            </tr>
            <%}}%>
            </tbody>
        </table>
            <button type="submit" style="width: 100px;height: 30px;border: none;background-color: lightcoral;margin-top: 20px" id="deleteButton">Xóa</button>
        </form>
    </div>
</section>
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