<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年1月11日 ^_^ 下午7:28:43 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
<title>尚硅谷会员登录页面</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

<script type="text/javascript">
	$(function() {
		
		$("#sub_btn").click(function() {
			
			//错误提示
			var $error = $("span.errorMsg");
			//正则表达式
			var patt = /^\w{5,12}$/;
			
			//验证用户名
			var userName = $("input[name='username']").val();
			if (!patt.test(userName)) {
				$error.text("您输入的用户名不合法");
				return false;
			}
			
			//验证密码
			var password = $("input[name='password']").val();
			if (!patt.test(password)) {
				$error.text("您输入的密码不合法");
				return false;
			}
			
			$error("");
			return true;
		});
		
	});




</script>
</head>
<body>

	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/logo.gif"  >
	</div>
	
		<div class="login_banner">
		
			<div id="l_content">
				<span class="login_word">欢迎登录</span>
			</div>
			
			<div id="content">
				<div class="login_form">
					<div class="login_box">
						<div class="tit">
							<h1>尚硅谷会员</h1>
							<a href="pages/user/regist.jsp">立即注册</a>
						</div>
						<div class="msg_cont">
							<b></b>
							<span class="errorMsg">
								${ empty error ? "请输入用户名和密码" : error }
							</span>
<%-- 								<%=request.getAttribute("error") == null ? "请输入用户和密码" : request.getAttribute("error") %> --%>
						</div>
						<div class="form">
							<form action="UserServlet" method="post">
								<input type="hidden" name="action" value="login"/>
								<label>用户名称：</label>
								<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" 
									value="${ cookie.logCook.value }"/>
<%-- 									value="${ empty username ? '' : username }"/> --%>
								<br />
								<br />
								<label>用户密码：</label>
								<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
								<br />
								<br />
								<input type="submit" value="登录" id="sub_btn" />
							</form>
						</div>
						
					</div>
				</div>
			</div>
		</div>

<!-- bottom 底部包含内容 -->	
<%@ include file="/pages/common/bottom.jsp" %>


</body>
</html>