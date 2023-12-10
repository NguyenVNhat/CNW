<%@ page import="cnw.Admin.Models.Bean.Instructor" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cnw.Admin.Models.Bean.Address" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        section{
            width: 100%;
            height: 500px;
            display: flex;
            justify-content: center;
            align-content: center;
        }
        section::-webkit-scrollbar{
            width: 10px;
        }
        .contain{
            width: 95%;
            height: 96%;
            display: flex;
            justify-content: space-between;
            overflow: auto;
        }
        .address{
            width: 30%;
            height: 100%;
        }
        .instructor{
            width: 68%;
            height: 100%;
        }
        .table-address{
            width: 100%;
        }
        thead{
            height: 60px;
            background-color: rgb(75, 165, 244);
        }
        td{
            text-align: center;
        }
        .tr1{
            height: 40px;
            background-color: rgb(204, 228, 249);
        }
        input{
            width: 140px;
            height: 40px;
            background-color: lightblue;
            border: none;
            border-radius: 4px;
            text-align: center;
            margin-bottom: 10px;
        }
        input:focus{
            border: 0.5px solid;
            border-color: lightgreen;
            outline: none;
        }
        a{
            text-decoration: none;
            color: #000;
        }
        .update-content{
            width: 100%;
            height: 500px;
            border: none;

        }
        button{
            width: 100px;
            height: 30px;
            border: none;
            border-radius: 4px;
            background-color: lightgreen;
        }
    </style>
</head>
<body>
<%
    ArrayList<Instructor> instructors = (ArrayList<Instructor>) request.getAttribute("instructors");
    ArrayList<Address> addresses = (ArrayList<Address>) request.getAttribute("addresses");
%>
<section>

    <div class="contain">
        <div class="address">
            <form action="../insaddress?action=addAddress" method="post">

                <input type="text" name="Id" placeholder="Id" required>
                <input type="text" name="AddressName" placeholder="address" required>
                <button type="submit">Thêm</button>
            </form>
            <form action="../insaddress?action=deleteAddress" method="post">
                <table class="table-address">
                    <thead>
                    <tr>
                        <th  style="height: 10%"><input id="selectAll" type="checkbox" style="width: 15px;height: 15px" /></th>
                        <th>ID</th>
                        <th>Address</th>
                        <th>#</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Address address: addresses
                        ) {
                    %>
                    <tr class="tr1">
                        <td  style="height: 10%"><input type="checkbox" class="rowCheckbox" style="width: 15px;height: 15px" name="IdAddress" value="<%=address.getId()%>"/></td>
                        <td><%=address.getId() %></td>
                        <td><%=address.getAddressName() %></td>
                        <td><a href="../insaddress?action=ToupdateAddress&Id=<%=address.getId()%>" target="update-content">Sửa</a></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>
                <button type="submit">Xóa</button>
            </form>
        </div>
        <div class="instructor">
            <form action="../insaddress?action=addIns" method="post">
                <input type="text" name="Id" placeholder="Id" required>
                <input type="text" name="Name" placeholder="Name" required>
                <input type="text" name="Age" placeholder="Age" required>
                <input type="text" name="Email" placeholder=" Email" required>
                <input type="text" name="Phone" placeholder="Number phone" required>

                <button type="submit">Thêm </button>
            </form>
            <table class="table-address">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>#</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Instructor instructor: instructors
                    ) {
                %>
                <tr class="tr1">
                    <td><%=instructor.getId() %></td>
                    <td><%=instructor.getName() %></td>
                    <td><%=instructor.getAge() %></td>
                    <td><%=instructor.getEmail() %></td>
                    <td><%=instructor.getPhone() %></td>
                    <td><a href="../insaddress?action=ToupdateIns&Id=<%=instructor.getId()%>" target="update-content" target="update-content">Sửa</a></td>
                </tr>
                <%}%>

                </tbody>
            </table>
        </div>
    </div>
</section>
<iframe class="update-content" id="update-content" name="update-content" >

</iframe>

<script>
    const selectAllCheckbox = document.getElementById('selectAll');
    const rowCheckboxes = document.querySelectorAll('.rowCheckbox');
    selectAllCheckbox.addEventListener('change', function() {
        rowCheckboxes.forEach(function(checkbox) {
            checkbox.checked = selectAllCheckbox.checked;
        });
    });

</script>
</body>
</html>
