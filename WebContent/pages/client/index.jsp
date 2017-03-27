<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年1月11日 ^_^ 下午7:45:51 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>书城首页</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

<script type="text/javascript">
	//页面加载完成之后
	$(function(){
		
		//给加入购物车添加ajax请求
		$("a.addcart").click(function() {
			//获取需要添加购物车的商品的Id
			var idv = $(this).attr("idv");
			// 为购物车库存添加属性获取并判断该商品的库存
			var idstock = $("#spanStock_" + idv).attr("idstock");
			if (idstock <= 0) {
				alert("火爆商品，来晚了，下次再来抢购！");
				return false;
			}
			$.getJSON(
				"CartServlet",
				"action=ajaxAddCart&id=" + idv + "&d=" + new Date(),
				function(data) {
// 					alert("商品添加购物车成功");
					$("#cartTotalCount").html("您的购物车中有" + data.totalCount + "件商品");
					$("#last_product_name").html("您刚刚将<span style=\"color: red\">" + data.last_product_name + "</span>加入到了购物车中");
				}
			);
			return false;
		});
		
		// 搜索按钮
		$("#searchPriceBtn").click(function(){
			
			var minPrice = $("#min").val();
			var maxPrice = $("#max").val();
			if (!$.isNumeric(minPrice) || !$.isNumeric(maxPrice)) {
				alert("请输入合法数字");
				return false;
			}
			if (minPrice < 0 || maxPrice < 0) {
				alert("请输入正数！");
				return false;
			}
			if (minPrice >= maxPrice) {
				alert("请输入合法的价格区间");
				return false;
			}
			return true
		});
		
		//为注销添加提醒事件
		$("a.logout").click(function() {
			return confirm("确定要退出吗？");
		});
	});

</script>

</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span  class="wel_word">网上书城</span>
			<div>
				<%
					Date date = new  Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					sdf.format(date);
				%>
				<c:choose>
					<c:when test="${empty username }">
						<a href="pages/user/login.jsp">登录</a> | 
						<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						<span style="color: red;">您好，${username }，欢迎回来！</span>
						<span style="color: blue;">当前时间是：<%=sdf.format(date) %></span> 
						<a class="logout" href="/book/UserServlet?action=quit">注销</a>&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
				<a href="pages/cart/cart.jsp">购物车</a>
				<c:if test="${adminB }">
					<a href="pages/manager/manager.jsp">后台管理</a>
				</c:if>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="BookIndexServlet" method="get">
					<input type="hidden" name="action" value="pageByPrice"/>
					价格：<input id="min" type="text" name="min" value="${ param.min }"> 元 - 
						<input id="max" type="text" name="max" value="${ param.max }"> 元 
						<input id="searchPriceBtn" type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<c:choose>
					<c:when test="${ sessionScope.cart.totalCount > 0 }">
						<span id="cartTotalCount">您的购物车中有${ sessionScope.cart.totalCount }件商品</span>
						<div id="last_product_name">
							您刚刚将<span style="color: red">${last_product_name }</span>加入到了购物车中
						</div>
					</c:when>
					<c:otherwise>
						<span id="cartTotalCount"></span>
						<br><div id="last_product_name">当前购物车为空！</div>
					</c:otherwise>
				</c:choose>
			</div>
			
		<c:choose>
		<%-- 如果 empty page.items 为真，说明没有图书  --%>
			<c:when test="${ empty page.list }">
				<div class="b_list">
					请添加图书
				</div>				
			</c:when>
			<%-- 如果 page.items 不为空，说明有书 --%>
		<c:otherwise>
			<c:forEach items="${page.list }" var="book">
<%-- 			${book } --%>
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${ book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span id="spanStock_${book.id }" idstock="${book.stock }" class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<a idv="${book.id }" class="addcart" href="CartServlet?action=addCart&id=${book.id }">加入购物车</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>			
			
			
			
			
		</div>
		

<%-- 分页 显示数据 --%>
<%@ include file="/pages/common/page.jsp"%>
	
	
	</div>

<!-- bottom -->	
<%@ include file="/pages/common/bottom.jsp" %>

</body>
</html>