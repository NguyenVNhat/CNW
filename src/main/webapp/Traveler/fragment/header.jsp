<%@ page import="cnw.utils.viewdto.SummaryProfile" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- header.jsp -->
<%SummaryProfile profile = (SummaryProfile)request.getSession().getAttribute("summaryProfile");%>
<header id="header" class="header fixed-top d-flex align-items-center">
  <div class="d-flex align-items-center justify-content-between">
    <a href="/traveler" class="logo d-flex align-items-center">
      <img src="/images/fragments/apple-touch-icon.png" alt="">
      <span class="d-none d-lg-block">Travel with me</span>
    </a>
    <i class="bi bi-list toggle-sidebar-btn"></i>
  </div><!-- End Logo -->

  <nav class="header-nav ms-auto">
    <ul class="d-flex align-items-center">
      <% if(profile != null) { %>
        <li class="nav-item dropdown pe-3">
        <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
          <img src="<%=profile.getAvatar() == null?"/images/avatars/default.png":("/images/avatars/"+profile.getAvatar())%>" alt="Profile" class="rounded-circle">
          <span class="d-none d-md-block dropdown-toggle ps-2">
            <%=profile.getName()%>
          </span>
        </a><!-- End Profile Image Icon -->

        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
          <li class="dropdown-header">
            <h6><%= profile.getName() %></h6>
            <span><%=profile.getUsername()%></span>
          </li>
          <li>
            <hr class="dropdown-divider">
          </li>

          <li>
            <a class="dropdown-item d-flex align-items-center" href="/traveler/update_profile">
              <i class="bi bi-person"></i>
              <span>Profile của bạn</span>
            </a>
          </li>
          <li>
            <a class="dropdown-item d-flex align-items-center" href="/traveler/change_password">
              <i class="bi bi-person"></i>
              <span>Đổi mật khẩu</span>
            </a>
          </li>
          <li>
            <hr class="dropdown-divider">
          </li>

          <li>
            <a class="dropdown-item d-flex align-items-center" href="/traveler/logout">
              <i class="bi bi-box-arrow-right"></i>
              <span>Đăng xuất</span>
            </a>
          </li>

        </ul><!-- End Profile Dropdown Items -->
      </li><!-- End Profile Nav -->
      <% } else { %>
      <a class="btn btn-primary m-2" href="/traveler/login" role="button">Đăng nhập</a>
      <% } %>
    </ul>
  </nav><!-- End Icons Navigation -->

</header><!-- End Header -->
