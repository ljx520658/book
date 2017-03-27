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
  <title>购物车页面</title>

<!-- 头部包含base标签  link script  -->  
<%@ include file="/pages/common/header.jsp" %>

  <script type="text/javascript">
  	$(function() {
		
  		//删除购物车商品 友好提示
  		$("a.deleteCartItem").click(function() {
			//方法一 父节点的父节点    中查找
			var name = $(this).parent().parent().find("td:first").text();
			//方法二 祖先节点tr  中查找
// 			var name = $(this).parents("tr").find("td:eq(0)").text();
			// 点击确定返回true,执行删除，点击取消，返回false 阻止删除
			if (confirm("确定要删除【 " + name + " 】的信息吗？")) {
				// ajax 请求地址 CartServlet?action=ajaxDeleteItem&id=${ item.value.id }
				$.get(
					"CartServlet",
					"action=ajaxDeleteItem&id=" + $(this).attr("delid") + "&d=" + new Date(),
					function(data) {
						$("span.b_count").html(data.totalCount);
						$("span.b_price").html(data.totalPrice);
						if (data.totalCount <=0 ) {
							$("#delete_Item").html("<tr><td>商品名称</td><td>数量</td><td>单价</td><td>金额</td><td>操作</td></tr><tr><td colspan=\"5\"><a href=\"${pageContext.request.contextPath }\">购物车现在还是空的，快去添加吧~</a></td></tr>");
							$("div.cart_info").hide();
						}
					},
					"JSON"
				);
				$(this).parents("tr").remove();
			}
			return false;
		});
		
  		//删除购物车商品 友好提示
  		$("a.deleteCartItem1").click(function() {
			var name = $(this).parent().parent().find("td:first").text();
			return confirm("确定要删除【 " + name + " 】的信息吗？");
		});
  		
  		// 给输入框添加onchange 事件
  		$(".updateCartItem").change(function() {
			//this 对象时当前正在执行事件的dom对象，获取现在输入框的值，使用this.value 是获取当前对象的值
			var nowCount = this.value;
			// defaultValues 可以获取文本输入框中value最初给的默认值
			// jQuery的方法获取自己添加的属性的值
			var itemId = $(this).attr("itemid");
			// confirm 对话框，确定返回true ，如果取消，放回false
			if (confirm("是否确认修改商品数量？")) {
				//发起请求
// 				location.href="CartServlet?action=updateCountCart&id=" + itemId + "&count=" + nowCount;
				// 使用ajax 发送请求
				$.getJSON(
					"CartServlet",
					"action=ajaxUpdateCount&id=" + itemId + "&count=" + nowCount + "&d=" + new Date(),
					function(data) {
// 						alert("修改数量成功");
						// 修改总数量
						$("span.b_count").html(data.totalCount);
						$("span.b_price").html(data.totalPrice);
						$("#update_item_" + itemId).html(data.itemTotalPrice);
					}
				);
			} else {
				// 还原 原来的个数
				this.value = this.defaultValue;
			}
		});
  		
  		// 清空购物车 方式一 使用id绑定单击事件 使用ajax 请求 给清空购物车功能  添加  确认提示
  		/*$("#clearCart1").click(function() {
  			if (confirm("您确定要清空购物车吗？")) {
  				// 发送请求 "CartServlet?action=clearCart"
				$.getJSON(
					"CartServlet",	
					"action=clearCart",	
					function(data) {
						$("#delete_Item").html("<tr><td>商品名称</td><td>数量</td><td>单价</td><td>金额</td><td>操作</td></tr><tr><td colspan=\"5\"><a href=\"${pageContext.request.contextPath }\">购物车现在还是空的，快去添加吧~</a></td></tr>");
						$("div.cart_info").hide();						
					}
				);
			}
		});*/
  		
	});
  	//清空购物车 方式二 使用onclick()事件
		function clearCart() {
  			if (confirm("您确定要清空购物车吗？")) {
  				// 发送请求 "CartServlet?action=clearCart"
				$.getJSON(
					"CartServlet",	
					"action=clearCart",	
					function(data) {
						$("#delete_Item").html("<tr><td>商品名称</td><td>数量</td><td>单价</td><td>金额</td><td>操作</td></tr><tr><td colspan=\"5\"><a href=\"${pageContext.request.contextPath }\">购物车现在还是空的，快去添加吧~</a></td></tr>");
						$("div.cart_info").hide();						
					}
				);
			}//if结束
		}
  	
/*   	// 删除购物车商品 方式二 使用onclick() 事件
  	function delitem(delid) {
		//方法二 祖先节点tr  中查找
			var name = $("a.deleteCartItem").parents("tr").find("td:eq(0)").text();
		// 点击确定返回true,执行删除，点击取消，返回false 阻止删除
		if (confirm("确定要删除【 " + name + " 】的信息吗？")) {
			// ajax 请求地址 CartServlet?action=ajaxDeleteItem&id=${ item.value.id }
			$.get(
				"CartServlet",
				"action=ajaxDeleteItem&id=" + delid + "&d=" + new Date(),
				function(data) {
					$("span.b_count").html(data.totalCount);
					$("span.b_price").html(data.totalPrice);
					if (data.totalCount <=0 ) {
						$("#delete_Item").html("<tr><td>商品名称</td><td>数量</td><td>单价</td><td>金额</td><td>操作</td></tr><tr><td colspan=\"5\"><a href=\"${pageContext.request.contextPath }\">购物车现在还是空的，快去添加吧~</a></td></tr>");
						$("div.cart_info").hide();
					}
// 					$("a.deleteCartItem").parent().parent().remove();
				},
				"JSON"
			);
		}
	} */
  	
  </script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

<!-- 登录成功后的菜单 -->
<%@ include file="/pages/common/login_success_menu.jsp" %>


	</div>
	
	<div id="main">
	
		<table id="delete_Item">
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:choose>
			<%-- 先判断，如果购物车有商品，则显示，没有则提示用户，购物车是空 --%>
				<c:when test="${ empty sessionScope.cart.items }">
					<tr>
						<td colspan="5">
							<a href="${ pageContext.request.contextPath }">购物车中还没有任何商品，去购物吧，小伙伴们！</a>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${sessionScope.cart.items }" var="item">
						<tr>
							<td>${ item.value.name }</td>
							<td><input itemid="${ item.value.id }" class="updateCartItem" style="width:25px;" type="text" value="${ item.value.count }"/></td>
							<td>${ item.value.price }</td>
							<td id="update_item_${item.value.id }">${ item.value.totalPrice }</td>
							<td><a delid="${ item.value.id }" onclick="delitem('${item.value.id }')" class="deleteCartItem" href="javascript:void(0);" 1href="CartServlet?action=deleteItem&id=${ item.value.id }">删除</a></td>
						</tr>	
					</c:forEach>
				</c:otherwise>
			</c:choose>		
		</table>
		<%-- 先判断，如果购物车有商品，则显示 --%>
		<c:if test="${ !empty sessionScope.cart.items }">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${ sessionScope.cart.totalCount }</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${ sessionScope.cart.totalPrice }</span>元</span>
				<span class="cart_span"><a id="clearCart" onclick="clearCart()" href="javascript:void(0);">清空购物车</a></span>
				<span class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
			</div>
		
		</c:if>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>