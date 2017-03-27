<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年2月8日 ^_^ 上午11:36:23 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>订单详情</title>
  
  <!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif" >
		<span class="wel_word">我的订单详情</span>
		<div>
			<div>
				<span>欢迎<span class="um_span">${username }</span>光临尚硅谷书城</span>
				<a href="/book/OrderServlet?action=myOrders">我的订单</a>
				<a class="quit" href="/book/UserServlet?action=quit">注销</a>&nbsp;&nbsp;
				<a href="index.jsp">返回</a>
			</div>
		</div>
	
	<div id="main">
		<center><span style="font-size: 30px;color: red;">订单号是：${param.orderId }，总金额是：${param.orderPrice}</span></center>
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
			</tr>
					<c:forEach items="${requestScope.orderDetails }" var="item">
						<tr>
							<td>${ item.name }</td>
							<td>${ item.count }</td>
							<td>${ item.price }</td>
							<td>${ item.totalPrice }</td>
						</tr>	
					</c:forEach>
			<tr>
				<c:if test="${empty requestScope.orderDetails }">
				亲，您还没有买过东西呢，去挑选喜欢的东东吧！
				</c:if>
			</tr>
		</table>
		<br><center><br>数量自己数吧，这个就不告诉你了！</center>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>