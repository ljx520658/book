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
  <title>订单管理页面</title>


<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

<!-- 图书管理 订单管理 返回商城 -->
<%@ include file="/pages/common/manager_header.jsp" %>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${ requestScope.allOrders }" var="order">
				<tr>
					<td>${ order.createTime }</td>
					<td>${ order.price }</td>
					<td><a href="OrderServlet?action=orderDetails&orderId=${order.orderId }&orderPrice=${order.price}">查看详情</a></td>
					<td>
						<c:choose>
							<%-- 如果订单状态status ==0 说明未发货 --%>
							<c:when test="${ order.status == 0 }">
								<a href="OrderServlet?action=sendOrder&orderId=${ order.orderId }">点击发货</a>
							</c:when>
							<%-- 如果订单状态status ==1 说明已发货 --%>
							<c:when test="${ order.status == 1  }">已发货</c:when>
							<%-- 如果订单状态status ==2 说明用户已签收 --%>
							<c:when test="${ order.status == 2 }">用户已签收</c:when>
						</c:choose>
					</td>
				</tr>	
			</c:forEach>		

		
		</table>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>