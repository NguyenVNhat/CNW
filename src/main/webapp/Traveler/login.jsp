<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Favicon  -->
    <link rel="icon" href="/images/favicon/favicon.png">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="/Traveler/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Traveler/vendor/bootstrap-icons/bootstrap-icons.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="/Traveler/css/main.css" rel="stylesheet">
    <title>Đăng nhập</title>

</head>
<body>
<%@include file="fragment/header.jsp" %>
<%@include file="fragment/sidebar.jsp" %>
<main id="main" class="main">
    <div class="pagetitle">
        <h1>Đăng nhập</h1>
    </div>
    <section class="section">
        <div class="row">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body">
<%--                        <div class="form">--%>
<%--                            <form class="form-login-main" name="form_1" action="/traveler/login" method="post">--%>
<%--                                <label for="username">Tên đăng nhập</label>--%>
<%--                                <input type="text" id="username" name="username" class="username" required />--%>
<%--                                <label for="password">Mật khẩu</label>--%>
<%--                                <input type="password" id="password" name="password" class="password" required />--%>
<%--                                <%@include file="fragment/notification.jsp" %>--%>
<%--                                <button type="submit">Đăng nhập</button>--%>
<%--                            </form>--%>
<%--                        </div>--%>
                        <!-- Custom Styled Validation -->
                        <form class="row g-3 needs-validation was-validated" novalidate="" action="/traveler/login" method="post">
                            <div class="col-md-12">
                                <label for="username" class="form-label">Tên đăng nhập</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                                <div class="invalid-feedback">
                                    Vui lòng nhập tên đăng nhập
                                </div>
                            </div>
                            <div class="col-md-12">
                                <label for="password" class="form-label">Mật khẩu</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                                <div class="invalid-feedback">
                                    Vui lòng nhập mật khẩu
                                </div>
                            </div>
                            <div class="col-12">
                                <button class="btn btn-primary" type="submit">Đăng nhập</button>
                            </div>
                        </form><!-- End Custom Styled Validation -->
                        <%@include file="fragment/notification.jsp" %>
                        <p>Bạn chưa đăng kí đúng không? <a href="/traveler/register">Đăng kí đi</a></p>
                    </div>
                </div>
            </div>
        </div>
    </section>

</main>
<script src="/Traveler/vendor/tinymce/tinymce.min.js"></script>
<script src="/Traveler/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/Traveler/js/main.js"></script>
</body>
</html>

