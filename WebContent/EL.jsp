<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  ^_^ 2017年1月12日 ^_^ 上午10:17:21 ^_^ -->
<html>
<head>
  <meta http-equiv="pragma" content="no-cache" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="Expires" content="0" />
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>title</title>
</head>
<body>
<%
	//首先我们需要在request域对象中设置一个属性
	request.setAttribute("hello", "这是内容");
%>
<%-- 获取请求域中的属性hello输出 --%>
jsp的输出：<%=request.getAttribute("hello") == null ? "" : request.getAttribute("hello")%><br/><br/>
<%-- 输出在域中查找输出hello的值 --%>
EL表达式的输出：${hello}<br/><br/>
<%-- <%=${hello} %> --%>

<%
// 	Map<String, String> map = new HashMap<>();
// map.put("color", "lanse");
// request.setAttribute("hello", map.keySet());
// request.getAttribute("hello");
// Map<String, String > map2=(Map<String, String>) request.getAttribute("hello");
// Set<String> set =  map2.keySet();
// System.out.print(set);
%>
<%=request.getAttribute("hello") %>
<br>设置属性：
<%request.setAttribute("abc", 123);%>
<br>获取属性：
<%request.getAttribute("abc");%>
<br>
<%=request.getAttribute("abc") %>
<br>使用EL获取：
<br>
${abc }
<br>
判断是否为空 ：${empty abc }
<br>
三元运算符： ${empty abc } ? "error" : "${abc }"
<br>
if判断：
  boolean a = ${empty abc }
   	
<%
	if( false){
		out.print("t");
	}else{
		out.print("f");
	}
%>
<br>

<br>

<br>
</body>
</html>