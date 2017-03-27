<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年1月11日 ^_^ 下午7:29:11 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>后台管理</title>

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
			<span class="wel_word">后台管理系统</span>

<!-- 图书管理 订单管理 返回商城 -->
<%@ include file="/pages/common/manager_header.jsp" %>


	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>