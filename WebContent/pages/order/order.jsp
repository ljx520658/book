<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年1月11日 ^_^ 下午7:29:11 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>我的订单</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
			<div>

<!-- 登录成功后的菜单 -->
<%@ include file="/pages/common/login_success_menu.jsp" %>


			</div>
	</div>
	
	<div id="main">
		
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>	
			<c:forEach items="${ requestScope.myOrders}" var="order">
				<tr>
					<td>${ order.createTime }</td>
					<td>${ order.price }</td>
					<c:choose>
						<c:when test="${ order.status == 0 }"><td>未发货</td></c:when>
						<c:when test="${ order.status == 1  }">
							<td><a href="OrderServlet?action=receiveOrder&orderId=${ order.orderId }">确认收货</a></td>
						</c:when>
						<c:when test="${ order.status == 2 }"><td>已签收</td></c:when>
					</c:choose>
					<td><a href="OrderServlet?action=orderDetails&orderId=${order.orderId }&orderPrice=${order.price}">查看详情</a></td>
				</tr>	
			</c:forEach>
			<tr>
				<c:if test="${empty requestScope.myOrders }">
				<td colspan="4">
					<a href="${pageContext.request.contextPath }">亲，您还没有买过东西呢，去挑选喜欢的东东吧！</a>
				</td>
				</c:if>
			</tr>	
		</table>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>