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
  <title>Danh sách tour du lịch</title>

</head>
<body>
<%@include file="fragment/header.jsp" %>
<%@include file="fragment/sidebar.jsp" %>
<main id="main" class="main">
  <div class="pagetitle">
    <h1>Danh sách tour du lịch</h1>
  </div>
  <section class="section">
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://i.kym-cdn.com/photos/images/newsfeed/001/166/022/976.gif" class="img-fluid rounded-start" alt="...">
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title">Card with an image on left</h5>
                    <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                  </div>
                </div>
              </div>
            </div><!-- End Card with an image on left -->
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

