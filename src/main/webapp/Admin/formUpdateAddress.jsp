<%@ page import="cnw.Admin.Models.Bean.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        form{
            margin: auto;
            width: 300px;
            height: 200px;
            background-color: #fff;
            border-radius: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-around;
        }
        input{
            text-align: center;
            height: 50px;
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
    Address address = (Address) request.getAttribute("address");
%>
    <form action="../insaddress?action=updateAddress" method="post" target="main-content">
        <input type="text" value="<%=address.getId()%>" name="Id" readonly>
        <input type="text" value="<%=address.getAddressName()%>" name="AddressName"  required>
        <button type="submit">Cập nhật </button>
    </form>
</body>
</html>
