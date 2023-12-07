<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page import="cnw.Admin.Models.Bean.Travel_Tour" %>
<%@ page import="cnw.Admin.Models.Bean.Traveler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
            width: 16.67%;
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
                Tất cả
            </div>
            <div class="item">
                Sắp tới
            </div>
            <div class="item">
                Hiện tại
            </div>
            <div class="item">
                Đã qua
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

    <% String pageID = (String) request.getAttribute("pageId");
        if(pageID.equals("1")) {
    %>
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
                <tr class="tr1" style="height: 30px;">
                    <td><input type="checkbox" /></td>
                    <td><%=item.getIdTour() %></td>
                    <td><%=item.getPrice() %></td>
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
                    <td>
                        <select>
                            <%
                                for (Traveler traveler: item.getListTraveler()
                                ) {
                            %>
                            <option><%=traveler.getName()%></option>
                            <%} %>
                        </select>
                    </td>

                </tr>
                <%}%>
            </tbody>
        </table>
    </div>

        <%}%>



</section>
</body>
</html>