<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
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
    <style>
        .findTour{
            width: 35%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: end;
        }
        .border{
            position: relative;
        }
        .formTime{
            position: absolute;
            bottom: 0;
            width: 80%;
            height: 60%;
            display: flex;
        }
        .iframe{
            background-color: #ffffff;
            border-radius: 10px;
            margin-top: 30px;
        }
        .table-list{
            width: 100%;
        }
        .thead{
            width: 100%;
            height: 60px;
        }
        th{
            border-bottom: 0.5px solid;
            width: 12.875%;
            font-size: 25px;
        }
        td{
            text-align: center;
        }
    </style>
</head>
<body>
<section class="contain">
    <section class="header">
        <div class="taskbar">
            <div class="item item-init"  style="border-bottom: solid;background-color: #cecece;">
                <a style="margin-right: 5px" href="../tour?action=getallTour" target="_self">Tất cả</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getUpTour" target="_self">Sắp tới</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=getDownTour" target="_self">Đã qua</a>
            </div>
            <div class="item">
                <a style="margin-right: 5px" href="../tour?action=ToAddTour" target="_self"><i class="fa-regular fa-plus"></i></a>
            </div>

        </div>
        <div class="findTour">
            <div class="divForm">
                <form action="" style="display: flex;width: 100%;height: 100%;">
                    <input type="text" name="textsearch" class="textsearch" />
                    <button type="submit" class="buttonSubmit">
                        OK
                    </button>
                </form>
            </div>
        </div>
        <div class="findByTime">
            <div class="border">
                <form class="formTime" action="">
                    <select  name="textsearch" class="textsearch_1">
                        <option value="1">Today</option>
                        <option value="2">Yesterday</option>
                        <option value="3">Last 7 days</option>
                        <option value="4">This month</option>
                        <option value="5">Last month</option>
                    </select>
                    <button type="submit" style="width: 17%;border-radius: 100px;border: 0.2px solid;">
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
                <th>Mã Tour</th>
                <th>Người hướng dẫn</th>
                <th>Giá </th>
                <th>Thời gian</th>
                <th>Địa điểm</th>
                <th>Trạng thái</th>
                <th>#</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<Tour> tours = (ArrayList<Tour>) request.getAttribute("tours");
                for (Tour item: tours
                ) {
            %>
            <tr class="tr1" style="height: 30px;">
                <td><input type="checkbox" /></td>
                <td><%=item.getId() %></td>
                <td><%=item.getIdInstructor() %></td>
                <td><%=item.getPrice() %></td>
                <td><%=item.getToTalTime() %></td>
                <td>
                    <select>
                    <%
                        for (String address: item.getListAddress()
                            ) {
                    %>
                        <option><%=address%></option>
                    <%} %>
                    </select>
                </td>
                <td><%=item.getStatus() %></td>
                <td>
                    <a style="margin-right: 5px" href="../tour?action=getdetailTour&Id=<%= item.getId()%>" target="_self"><i class="fa-regular fa-eye"></i></a>
                    <a style="margin-right: 5px" href="../tour?action=ToupdateTour&Id=<%= item.getId()%>"><i class="fa-regular fa-pen-to-square"></i></a>
                    <a href="../tour?action=TodeleteTour&Id=<%= item.getId()%>"><i class="fa-regular fa-trash-can" ></i></a>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>



</section>
</body>
</html>