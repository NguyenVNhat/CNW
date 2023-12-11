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
  <title>Đổi mật khẩu</title>

</head>
<body>
<%@include file="fragment/header.jsp" %>
<%@include file="fragment/sidebar.jsp" %>
<main id="main" class="main">
  <div class="pagetitle">
    <h1>Đổi mật khẩu</h1>
  </div>
  <section class="section">
    <div class="row">
      <div class="col-lg-6">
        <div class="card">
          <div class="card-body">
            <form class="row g-3 needs-validation was-validated" novalidate="" action="/traveler/change_password" method="post">
              <div class="col-md-12">
                <label for="oldPassword" class="form-label">Mật khẩu hiện tại</label>
                <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                <div class="invalid-feedback">
                  Vui lòng nhập mật khẩu
                </div>
              </div>
                <div class="col-md-12">
                    <label for="newPassword" class="form-label">Mật khẩu mới</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    <div class="invalid-feedback">
                    Vui lòng nhập mật khẩu mới
                    </div>
                </div>
                <div class="col-md-12">
                    <label for="confirmPassword" class="form-label">Nhập lại mật khẩu mới</label>
                    <input type="password" class="form-control" id="confirmPassword" required>
                    <div class="invalid-feedback">
                    Vui lòng nhập lại mật khẩu mới
                    </div>
                </div>
              <p class="alert-danger" id="error"></p>
              <div class="col-12">
                <button class="btn btn-primary" onclick="checkConfirmPassword()" type="submit">Đăng nhập</button>
              </div>
            </form><!-- End Custom Styled Validation -->
            <%@include file="fragment/notification.jsp" %>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>
<script>
  function checkConfirmPassword() {
    let newPassword = document.getElementById("newPassword").value;
    let confirmNewPassword = document.getElementById("confirmNewPassword").value;
    if (newPassword !== confirmNewPassword) {
      document.getElementById("error").innerHTML = "Mật khẩu không khớp";
    }
  }
</script>
<script src="/Traveler/vendor/tinymce/tinymce.min.js"></script>
<script src="/Traveler/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/Traveler/js/main.js"></script>
</body>
