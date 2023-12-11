<%@ page import="cnw.Traveler.Model.Bean.Traveler" %>
<%@ page import="cnw.utils.servlet.FlashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  Traveler traveler = (Traveler) request.getAttribute("traveler");
  FlashMap flashMap = (FlashMap) request.getAttribute("flashMap");
  Map<String, String> params = flashMap.getTargetRequestParams();
  String name = params.get("name") == null?traveler.getName():params.get("name");
  String address = params.get("address") == null?traveler.getAddress():params.get("address");
  Date dayBorn = params.get("dayBorn") == null?traveler.getDayBorn():Date.valueOf(params.get("dayBorn"));
  String email = params.get("email") == null?traveler.getEmail():params.get("email");
  String phone = params.get("phone") == null?traveler.getPhone():params.get("phone");
  String avatar = params.get("avatar") == null?traveler.getAvatar():params.get("avatar");
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
  <title>Cập nhật hồ sơ</title>

</head>
<body>
<%@include file="fragment/header.jsp" %>
<%@include file="fragment/sidebar.jsp" %>
<main id="main" class="main">
  <div class="pagetitle">
    <h1>Cập nhật hồ sơ</h1>
  </div>
  <section class="section">
    <div class="row">
      <div class="col-lg-6">
        <div class="card">
          <div class="card-body">
            <form class="row g-3 needs-validation was-validated" novalidate="" action="/traveler/update_profile" method="post" enctype="multipart/form-data">
              <div class="col-md-12">
                <label for="name" class="form-label">Tên</label>
                <input type="text" class="form-control" id="name" name="name" value="<%=name != null?name:""%>" required>
                <div class="invalid-feedback">
                  Vui lòng nhập tên tên
                </div>
              </div>
                <div class="col-md-12">
                    <label for="address" class="form-label">Địa chỉ</label>
                    <input type="text" class="form-control" id="address" name="address" value="<%=address != null?address:""%>">
                    <div class="invalid-feedback">
                    Vui lòng nhập địa chỉ
                    </div>
                </div>
                <div class="col-md-12">
                    <label for="dayBorn" class="form-label">Ngày sinh</label>
                    <input type="date" class="form-control" id="dayBorn" name="dayBorn" value="<%=dayBorn != null?dayBorn:""%>" >
                </div>
                <div class="col-md-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="<%=email != null?email:""%>" required>
                    <div class="invalid-feedback">
                    Vui lòng nhập email
                    </div>
                </div>
                <div class="col-md-12">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="<%=phone != null?phone:""%>">
                </div>
                <div class="col-md-12">
                    <label for="avatar" class="form-label">Hình đại diện</label>
                    <input type="file" class="form-control" id="avatar" accept="image/*" name="avatar">
                    <img id="avatarPreview" class="mt-2 rounded" <%if(avatar==null) {%>hidden="hidden"<%}%> src="<%=avatar != null?("/images/avatars/"+avatar):""%>" width="100%" alt="Preview">
                </div>
              <%@include file="fragment/notification.jsp" %>
              <div class="col-12">
                <button class="btn btn-primary" type="submit">Lưu</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</main>

<script>
  document.getElementById('avatar').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function(e) {
        const preview = document.getElementById('avatarPreview');
        preview.src = e.target.result;
        preview.style.display = 'block';
        preview.removeAttribute('hidden');
      }
      reader.readAsDataURL(file);
    }
  });
</script>
<script src="/Traveler/vendor/tinymce/tinymce.min.js"></script>
<script src="/Traveler/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="/Traveler/js/main.js"></script>
</body>
</html>
