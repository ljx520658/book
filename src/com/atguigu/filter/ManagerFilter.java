package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.User;
/**
 * 访问管理页面时，过滤管理员是否登录
 * @author Administrator
 *
 */
@WebFilter("/pages/manager/*")
public class ManagerFilter implements Filter {

    public ManagerFilter() { }

	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String	username = (String) httpServletRequest.getSession().getAttribute("username");
		if (username != null) {
			System.out.println("用户已登录，放行");
			chain.doFilter(request, response);
		} else {
			System.out.println("用户未登录，跳转至登录页面");
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/pages/user/login.jsp");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
