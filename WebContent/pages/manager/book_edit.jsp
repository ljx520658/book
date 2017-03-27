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
  <title>编辑图书</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>

<!-- 图书管理 订单管理 返回商城 -->
<%@ include file="/pages/common/manager_header.jsp" %>


		</div>
		
		<div id="main">
			<form action="/book/manager/BookServlet" method="post">
				<!-- 设置隐藏域，用于接收book_manager.jsp 页面转发过来pageNo参数  ，然后随表单提交一起提交 -->
				<input type="hidden" name="pageNo" value="${param.pageNo }"/>
				<!--  判断 book 是否有值，有值则说明是修改操作，没有值则说明是添加一本新书，即new 一个Book 新对象 -->
				<c:choose>
					<c:when test="${empty book }">
						<input type="hidden" name="action" value="addBook"/>
					</c:when>
					<c:otherwise>
						<input type="hidden" name="action" value="updateBook"/>
						<input type="hidden" name="id" value="${book.id }"/>
					</c:otherwise>
				</c:choose>
				<input type="hidden" name="action" value="updateBook"/>
<%-- 				<input type="hidden" name="action" value="${empty book ? 'addBook' : 'updateBook' }"/> --%>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>	
						
					<tr>
						<td><input name="name" type="text" value="${book.name }"/></td>
						<td><input name="price" type="text" value="${book.price }"/></td>
						<td><input name="author" type="text" value="${book.author }"/></td>
						<td><input name="sales" type="text" value="${book.sales }"/></td>
						<td><input name="stock" type="text" value="${book.stock }"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>