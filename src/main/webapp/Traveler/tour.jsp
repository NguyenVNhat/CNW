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
  <title>Danh sách tour đã đăng ký</title>

</head>
<body>
<%@include file="fragment/header.jsp" %>
<%@include file="fragment/sidebar.jsp" %>
<main id="main" class="main">
  <div class="pagetitle">
    <h1>Danh sách tour đã đăng ký</h1>
  </div>
  <section class="section">
    <div class="row">
      <div class="col-lg-12">
        <div class="card">
          <div class="card-body">
            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
                    <div class="carousel-inner">
                      <div class="carousel-item active">
                        <img src="https://www.vietnambooking.com/wp-content/uploads/2022/10/dong-phong-nha-o-quang-binh-2.jpg" class="d-block w-100" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                          <p>Động Phong Nha - Quảng Bình</p>
                        </div>
                      </div>
                    </div>

                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                      <span class="visually-hidden">Trang trước</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                      <span class="carousel-control-next-icon" aria-hidden="true"></span>
                      <span class="visually-hidden">Trang tiếp</span>
                    </button>

                  </div><!-- End Slides with fade transition -->
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title">Tour 2</h5>
                    <p class="card-text">
                      Tour du lịch này đưa du khách đến ba điểm đến tuyệt vời của Việt Nam. Đầu tiên là Động Phong Nha, một kỳ quan thiên nhiên với hệ thống hang động kỳ diệu, rừng núi xanh ngút ngàn và cảnh quan hùng vĩ. Tiếp theo là Bà Nà Hill, một điểm đến nổi tiếng với cáp treo dẫn lên đỉnh núi, nơi du khách có thể tận hưởng khung cảnh tuyệt đẹp của Đà Nẵng từ trên cao.
                      <br>
                      Giá: 1000000 VND <br>
                      Thời gian khởi hành: 15/12/2023 <br>
                      Tổng thời gian tour: 3 ngày <br>
                    </p>
                    <div class="row">
                      <div class="col-md-6">
                        <a href="#" class="btn btn-primary">Hủy đăng ký</a>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
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

