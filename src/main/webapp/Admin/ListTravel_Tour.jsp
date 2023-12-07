<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
<%@ page import="cnw.Admin.Models.Bean.Traveler" %>
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
                <a style="margin-right: 5px" href="../tour?action=getallTour" target="_self">Tất cả</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getUpTour" target="_self">Sắp tới</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getDownTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getCurrentTour" target="_self">Hiện có</a>
            </div>
            <%} else if (pageId.equals("2")) {

            %>
            <div class="item "  >
                <a style="margin-right: 5px" href="../tour?action=getallTour" target="_self">Tất cả</a>
            </div>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../tour?action=getUpTour" target="_self">Sắp tới</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getDownTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getCurrentTour" target="_self">Hiện có</a>
            </div>
            <%} else if (pageId.equals("3")) {
            %>
            <div class="item "  >
                <a style="margin-right: 5px" href="../tour?action=getallTour" target="_self">Tất cả</a>
            </div>
            <div class="item ">
                <a style="margin-right: 5px" href="../tour?action=getUpTour" target="_self">Sắp tới</a>
            </div>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../tour?action=getDownTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getCurrentTour" target="_self">Hiện có</a>
            </div>
            <%} else if (pageId.equals("4")) {
            %>
            <div class="item "  >
                <a style="margin-right: 5px" href="../tour?action=getallTour" target="_self">Tất cả</a>
            </div>
            <div class="item ">
                <a style="margin-right: 5px" href="../tour?action=getUpTour" target="_self">Sắp tới</a>
            </div>
            <div class="item " >
                <a style="margin-right: 5px" href="../tour?action=getDownTour" target="_self">Đã qua</a>
            </div>
            <div class="item item-init" style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../tour?action=getCurrentTour" target="_self">Hiện có</a>
            </div>
            <%}%>

        </div>
        <div class="findTour">
            <div class="divForm">
                <form action="../tour?action=findTour&typeSearch=<%=pageId%>" method="post" style="display: flex;width: 100%;height: 100%;">
                    <input type="text" name="textsearch" class="textsearch" />
                    <button type="submit" class="buttonSubmit">
                        OK
                    </button>
                </form>
            </div>
        </div>
        <div class="findByTime">
            <div class="border">
                <form class="formTime" action="../tour?action=findTourDay&pageId=<%=pageId%>" method="post">
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

        <table class="table-list">
            <thead class="thead">
            <tr>
                <th style="width: 5%;"><input type="checkbox" /></th>
                <th>Id</th>
                <th>Price</th>
                <th>Address</th>
                <th>Traveler</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Travel_Tour> tours = (ArrayList<Travel_Tour>) request.getAttribute("tours");
                for (Travel_Tour item: tours
                ) {
            %>
            <tr class="tr1">
                <td><input type="checkbox" /></td>
                <td><%=item.getIdTour() %></td>
                <td><%=item.getPrice() %></td>
                <td style="position: relative;" class="divmodal">
                    <button class="button_modal">
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
                <td style="position: relative;" class="divmodal">
                    <button class="button_modal">
                        View
                    </button>
                    <div class="modal" >
                        <%
                            if(item.getListTraveler().isEmpty())
                            {
                        %>
                        <p style="margin-left: 10px;margin-top: 20px">Chưa chọn địa điểm cho tour này</p>
                        <%}
                        else {
                            Integer index = 1;
                            for (Traveler travel: item.getListTraveler()
                            ) {
                        %>
                        <p style="margin-left: 10px;margin-top: 10px">Thành viên <%=index%> : <%=travel.getName()%></p>
                        <% index++;}} %>
                    </div>
                </td>

                <td>
                    <a style="margin-right: 10px;" href="../tour?action=getdetailTour&Id=<%= item.getIdTour()%>" target="_self"><i class="fa-regular fa-eye"></i></a>
                    <a style="margin-right: 10px;" href="../tour?action=ToupdateTour&Id=<%= item.getIdTour()%>"><i class="fa-regular fa-pen-to-square"></i></a>
                    <a href="../tour?action=TodeleteTour&Id=<%= item.getIdTour()%>"><i class="fa-solid fa-square-minus"></i></a>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>

    </div>
</section>


</body>
</html>