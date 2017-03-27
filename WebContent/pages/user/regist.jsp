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
  <title>尚硅谷会员注册页面</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

<style type="text/css">
	.login_form{
		height:430px;
		margin-top: 25px;
		
		
		
		
		
		
		
	}
	
</style>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		//获取错误提示标签
		var $error = $("span.errorMsg");	
		//验证用户名是否存在
		$("#username").blur(function() {
			// 获取用户名
			var usernameValue = this.value;
			// 判断用户名不能为空
			if (usernameValue == "") {
				$error.html("用户名不能为空！");
				return;
			}
			// 发送ajax 请求验证
			$.get(
				"UserServlet",
				"action=ajaxExistUsername&username=" + this.value + "&d=" + new Date(),
				function(data) {
					if (data.isExist) {
						$error.html("");
					} else {
						$error.html("用户名已存在");
					}
				},
				"json"
			);
			
		});
		
		//注册按钮的单击事件
		$("#sub_btn").click(function() {

			
			//创建正则表达式
			var patt = /^\w{5,12}$/;
			var userName = $("#username").val();
			if (!patt.test(userName)) {
				$error.text("用户名不合法！");
				return false;
			}
			
			var password = $("#password").val();
			if (!patt.test(password)) {
				$error.text("输入的密码不合法");
				return false;
			}
			
			var repwd = $("#repwd").val();
			if (repwd != password) {
				$error.text("两次输入的密码不一致");
				return false;
			}
			
			var email = $("#email").val();
			var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!emailPatt.test(email)) {
				$error.text("您输入的邮箱不合法！");
				return false;
			}
			
			//验证码 
			var code = $("#code").val();
// 			code = $.trim(code);
			if ($.trim(code) == "") {
				$error.text("验证码不能为空！");
				return false;
			}
			
			$error.text("");
			return true;
		});
		
		//验证码的单击事件
		$("#imgCode").click(function() {
// 			alert(this.src); //获取当前对象的src地址
			this.src="Kaptcha.jpg?d=" + new Date();
			
		});
		
	});


</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								 
								<span class="errorMsg">${msg }	</span>
<%-- 									<%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%> --%>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" 
										value="${param.username }"/>
<%-- 										value="${empty username ? '' : username }"/> --%>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" 
										value="${param.email }"/>
<%-- 										value="${empty email ? '' : email }"/> --%>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 130px;" name="code" id="code"/>
									<img id="imgCode" alt="验证码找不到" src="Kaptcha.jpg" style="float: right; width:100px; height:36px; margin-right: 50px ">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									<a href="pages/user/login.jsp"><span style="color: red;">已有账号->立即登录</span></a>
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