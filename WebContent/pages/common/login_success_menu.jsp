<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 登录成功后的菜单 -->
				<div>
					<span>欢迎<span class="um_span">${username }</span>光临尚硅谷书城</span>
					<a href="/book/OrderServlet?action=myOrders">我的订单</a>
					<a class="quit" href="/book/UserServlet?action=quit">注销</a>&nbsp;&nbsp;
					<a href="index.jsp">返回</a>
				</div>

				
	<script type="text/javascript">
		$(function() {
			$("a.quit").click(function() {
				return confirm("确定要退出吗？");
			});
		});
	
	</script>