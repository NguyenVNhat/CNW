<%@ page import="java.util.Map" %>
<%@ page import="cnw.utils.servlet.FlashMap" %>
<%@ page import="cnw.utils.viewdto.NotificationType" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<% FlashMap flashMap2 = (FlashMap) request.getAttribute("flashMap");
if (flashMap2 != null) {
    for (Map.Entry<String, String> entry : flashMap2.getTargetRequestParams().entrySet()) {
        String key = entry.getKey();
        Object value = entry.getValue();
        if (key.equals(String.valueOf(NotificationType.INFO))){
%><div class="alert alert-info"><%=value%></div> <%
    }
    if (key.equals(String.valueOf(NotificationType.ERROR))){
%><div class="alert alert-danger"><%=value%></div> <%
            }
        }
    }
%>
