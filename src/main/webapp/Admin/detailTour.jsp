<%@ page import="cnw.Admin.Models.Bean.Tour" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 04/12/2023
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Tour tour = (Tour) request.getAttribute("detailTour");
%>
<p><%= tour.getId() %></p>
<p><%= tour.getPrice() %></p>
<p><%= tour.getIdInstructor() %></p>
<div>
    <select>
        <%
            for (String address: tour.getListAddress()
            ) {
        %>
        <option><%=address%></option>
        <%} %>
    </select>
</div>
<p><%= tour.getToTalTime() %></p>
<p><%= tour.getTimeStart() %></p>
<p><%= tour.getStatus() %></p>
</body>
</html>
