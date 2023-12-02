
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/Admin/asset/css/AdminLogin.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nova+Square&display=swap" rel="stylesheet">
</head>
<body>
<div class="blur-background"></div>
<div class="form-login">
    <div class="image-welcome">
        <div class="accounce">
            <p style="font-family: 'Nova Square', sans-serif;
                        font-size: 40px;
                        font-weight: 700; color: #3b3b3b;">WELCOME</p>
            <small>Hi, welcome to our website</small>
        </div>
    </div>
    <div class="div-form">
        <div class="circle circle-1"> </div>
        <div class="circle circle-2"> </div>
        <div class="circle circle-3"> </div>
        <div class="main-form">
            <div class="div-title">
                <div class="title">LOGIN</div>
            </div>
            <div class="form">
                <form class="form-login-main" name="form_1" action="../amdin-login?action=adminlogin" method="post">
                    <input type="text" id="Adminname" name="Adminname" class="Adminname" placeholder="Admin name" />
                    <input type="text" id="Password" name="Password" class="Password" placeholder="Password" />
                    <a class="forgetpassword" href="#">Forget Password ?</a>
                    <%
                        String error = (String) request.getAttribute("Error");
                        if (error==null)
                            error="";
                    %>
                    <p id="text_error" class="p_text" style="margin: 0 auto;margin-top: 10px"><%=error%></p>
                    <button type="submit" onclick="return handleSubmit()" class="button-login">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    let a = document.querySelector(".p_text");
    function handleSubmit() {
        let username = document.form_1.Adminname.value;
        let password = document.form_1.Password.value;

        if (password ==="" || username === "")
        {
            a.innerHTML = "Vui lòng nhập thông tin"
            return false
        }
    }
</script>
</body>
</html>

