<%@ page import="cnw.Admin.Models.Bean.Tour" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Instructor" %><%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 04/12/2023
  Time: 23:45
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
    ArrayList<Instructor> instructors = (ArrayList<Instructor>) request.getAttribute("detailIns");

%>

    <form action="../tour?action=updateTour" method="post">
        <input type="text" name="Id" value="<%=tour.getId()%>"  readonly/>
        <select type="text" name="Intructor" >
            <%
                for (Instructor instructor:instructors
                     ) {
            %>
                <option value="<%=instructor.getId()%>"><%=instructor.getName()%></option>
            <%}%>

        </select>
        <input type="text" name="Price"  value="<%=tour.getPrice()%>"/>
        <input type="text" name="TotalTime"  value="<%=tour.getToTalTime()%>"/>
        <input type="text" name="TimeStart"  value="<%=tour.getTimeStart()%>"/>
        <input type="text" name="Status"  value="<%=tour.getStatus()%>"/>
        <button type="submit">OK</button>
    </form>
</body>
</html>
