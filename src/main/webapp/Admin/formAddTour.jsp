<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
<%@ page import="java.util.ArrayList" %><%--
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

    ArrayList<Instructor> instructors = (ArrayList<Instructor>) request.getAttribute("detailIns");

%>
<form action="../tour?action=addTour" method="post">
    Id :<input type="text" name="Id" />
    <select type="text" name="Intructor" >
        <%
            for (Instructor instructor:instructors
            ) {
        %>
        <option value="<%=instructor.getId()%>"><%=instructor.getName()%></option>
        <%}%>

    </select>
    Price:<input type="text" name="Price"  />
    ToTalTime :<input type="text" name="TotalTime"  />
    StartTime: <input type="date" name="TimeStart"  />
    Status:<input type="text" name="Status" />
    <button type="submit">OK</button>
</form>
</body>
</html>
