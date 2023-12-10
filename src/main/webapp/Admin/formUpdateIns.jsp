<%@ page import="cnw.Admin.Models.Bean.Address" %>
<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        form{
            margin: auto;
            width: 300px;
            height: 300px;
            background-color: #fff;
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-around;
        }
        input{
            text-align: center;
            height: 40px;
            border: none;
            width: 90%;
            border-radius: 4px;
            background-color: lightgreen;
        }
        input:focus{
            outline: none;
        }
        button{
            width: 100px;
            height: 30px;
            border: none;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<%
    Instructor instructor = (Instructor) request.getAttribute("instructors");
%>
<form action="../insaddress?action=updateIns" method="post" target="main-content">
    <input type="text" value="<%=instructor.getId()%>" name="Id" readonly>
    <input type="text" value="<%=instructor.getName()%>" name="Name"  required>
    <input type="text" value="<%=instructor.getAge()%>" name="Age"  required>
    <input type="text" value="<%=instructor.getEmail()%>" name="Email"  required>
    <input type="text" value="<%=instructor.getPhone()%>" name="Phone"  required>

    <button type="submit">Cập nhật </button>
</form>
</body>
</html>
