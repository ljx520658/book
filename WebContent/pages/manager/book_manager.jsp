<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年1月11日 ^_^ 下午7:29:11 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

	<script type="text/javascript">
		$(function() {
			$("a.delBook").click(function() {
				//方法一 父节点的父节点    中查找
				var name = $(this).parent().parent().find("td:first").text();
				//方法二 祖先节点tr  中查找
// 				var name = $(this).parents("tr").find("td:eq(0)").text();
				// 点击确定返回true,执行删除，点击取消，返回false 阻止删除
				return confirm("确定要删除 " + name + " 书的信息吗？");
			});
		});
		
	</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>

<!-- 图书管理 订单管理 返回商城 -->
<%@ include file="/pages/common/manager_header.jsp" %>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${page.list }" var="book">
				<tr>
					<td>${book.name }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td><a href="/book//manager/BookServlet?action=getBook&id=${book.id }&pageNo=${param.pageNo}">修改</a></td>
					<td><a class="delBook" href="/book/manager/BookServlet?action=delBook&id=${book.id }&pageNo=${param.pageNo}">删除</a></td>
				</tr>	
			</c:forEach>		
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?&pageNo=${param.pageNo}">添加图书</a></td>
			</tr>	
		</table>
	</div>
		<br>

<%-- 分页 显示数据 --%>
<%@ include file="/pages/common/page.jsp"%>
	
		<br>
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>