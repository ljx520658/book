<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  ^_^ 2017年1月18日 ^_^ 下午6:58:48 ^_^ -->

<%-- 分页 显示数据 --%>
	<div id="page_nav">
		<a href="${ page.url }&pageNo=1">首页</a>
		<!-- 判断是否是第一页，第一页没有上一页 -->
		<c:if test="${page.pageNo > 1 }">
			<a href="${ page.url }&pageNo=${page.pageNo - 1 }">上一页</a>
		</c:if>
		
		<%-- 显示5页信息 分两种，总页数<=5 和  总页数 >5 --%>
		<c:choose>
			<c:when test="${page.pageTotal <=5 }">
				<c:set var="begin" value="1"></c:set>
				<c:set var="end" value="${page.pageTotal }"></c:set>
			</c:when>
			<%--第二种情况 判断总页数大于5的情况 --%>
			<c:when test="${page.pageTotal > 5 }">
				<c:choose>
					<%-- 1、当前页码为前3页，小于等于3 --%>
					<c:when test="${page.pageNo <= 3 }">
						<c:set var="begin" value="1"></c:set>
						<c:set var="end" value="5"></c:set>
					</c:when>
					<%-- 2、当前页面为最后3页 --%>
					<c:when test="${page.pageNo > page.pageTotal -3 }">
						<c:set var="begin" value="${page.pageTotal - 4 }"></c:set>
						<c:set var="end" value="${page.pageTotal }"></c:set>
					</c:when>
					<%-- 3、其他正常显示页码 begin=pageNo -2 ，end=pageNo+2 --%>
					<c:otherwise>
						<c:set var="begin" value="${page.pageNo - 2 }"></c:set>
						<c:set var="end" value="${page.pageNo + 2 }"></c:set>
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>
		
		<%-- 根据begin end 开始遍历 分页数据 --%>
		<c:forEach var="p" begin="${begin }" end="${end }">
			<%-- 如果遍历时 等于当前页码 则 不显示链接 --%>
			<c:if test="${page.pageNo == p }">
				【${p }】
			</c:if>
			<c:if test="${page.pageNo  != p}">
				<a href="${ page.url }&pageNo=${p }">${p }</a>
			</c:if>
		</c:forEach>
		
		<%-- 设置下一页是否显示   末页 --%>
		<c:if test="${page.pageNo < page.pageTotal }">
			<a href="${ page.url }&pageNo=${page.pageNo + 1 }">下一页</a>
		</c:if>
		<a href="${ page.url }&pageNo=${page.pageTotal }">末页</a>
		共${page.pageTotal }页，${page.totalCount }条记录 到第<input value="" name="pn" id="pn_input"/>页
		<input id="pn_button" type="button" value="确定">
	</div>
	
	<%-- 为跳转框设置 页面跳转及连接 --%>
	<script type="text/javascript">
		$(function() {
			$("#pn_button").click(function() {
				var pn = $("#pn_input").val();
				// location 表示浏览器地址栏的路径信息 可读 可写 
// 				alert(location.href);
				location.href="${ page.url }&pageNo=" + pn;
				
			});
		});
	</script>