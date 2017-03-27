<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 转发到Servlet程序去获取首页需要显示的数据 --%>
<jsp:forward page="BookIndexServlet?action=pageByPrice"></jsp:forward>