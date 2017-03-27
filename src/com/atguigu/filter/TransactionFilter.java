package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.atguigu.utils.JDBCUtil;

/**用户事务管理  过滤所有请求
 */
@WebFilter("/*")
public class TransactionFilter implements Filter {

    public TransactionFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			// 放行请求，让代码执行到实现访问的资源中
			chain.doFilter(request, response);
			// 关闭连接，并提交事务
			JDBCUtil.commitAndCloseConnection();
		} catch (Exception e) {
			// 如果失败 回滚事务
			JDBCUtil.rollbackAndCloseConnection();
			throw new RuntimeException(e);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
