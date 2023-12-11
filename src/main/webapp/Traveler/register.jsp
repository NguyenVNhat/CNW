<%@ page import="cnw.utils.servlet.FlashMap" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  FlashMap flashMap = (FlashMap) request.getAttribute("flashMap");
  Map<String, String> params = flashMap.getTargetRequestParams();
  String username = params.get("username");
  String password = params.get("password");
  String name = params.get("name");
  String email = params.get("email");
%>
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
  <title>Đăng kí</title>

</head>
<body>
<%@include file="fragment/header.jsp" %>
<%@include file="fragment/sidebar.jsp" %>
<main id="main" class="main">
  <div class="pagetitle">
    <h1>Đăng kí</h1>
  </div>
  <section class="section">
    <div class="row">
      <div class="col-lg-6">
        <div class="card">
          <div class="card-body">
            <form class="row g-3 needs-validation was-validated" novalidate="" action="/traveler/register" method="post">
              <div class="col-md-12">
                <label for="username" class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" id="username" name="username" value="<%=username != null?username:""%>" required>
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
              <div class="col-md-12">
                <label for="confirmPassword" class="form-label">Nhập lại mật khẩu</label>
                <input type="password" class="form-control" id="confirmPassword" required>
                <div class="invalid-feedback">
                  Vui lòng nhập lại mật khẩu
                </div>
                  <p class="alert alert-danger" id="incorect-password-feedback" hidden>Mật khẩu không khớp</p>
              </div>
              <div class="col-md-12">
                  <label for="name" class="form-label">Họ và tên</label>
                  <input type="text" class="form-control" id="name" name="name" value="<%=name != null?name:""%>" required>
                  <div class="invalid-feedback">
                  Vui lòng nhập họ và tên
                  </div>
              </div>
              <div class="col-md-12">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" id="email" name="email" value="<%=email != null?email:""%>" required>
                  <div class="invalid-feedback">
                  Vui lòng nhập email
                  </div>
              </div>
              <div class="col-12">
                <button class="btn btn-primary" type="submit" onclick="checkConfirmPassword(event)">Đăng kí</button>
              </div>
              <%@include file="fragment/notification.jsp" %>
            </form><!-- End Custom Styled Validation -->
            <p>Bạn có tài khoản rồi đúng không? <a href="/traveler/login">Đăng nhập đi</a></p>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>
<script>
  function checkConfirmPassword(event) {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;
    if (password !== confirmPassword) {
      document.getElementById("incorect-password-feedback").removeAttribute("hidden");
      event.preventDefault(); // This prevents the form submission
    }
  }
</script>
<script src="/Traveler/vendor/tinymce/tinymce.min.js"></script>
<script src="/Traveler/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/Traveler/js/main.js"></script>
</body>
</html>