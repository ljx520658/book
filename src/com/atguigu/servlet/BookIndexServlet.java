package com.atguigu.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.StringToIntLong;

@WebServlet("/BookIndexServlet")
public class BookIndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService;
	
    public BookIndexServlet() {
        super();
        bookService = new BookServiceImpl();
    }

    /**首页 按价格 分页显示图书列表*/
    protected void pageByPrice(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("查看登录服务器的请求IP地址");
		String remoteHost = request.getRemoteHost();
		String remoteAddr = request.getRemoteAddr();
//		String remoteUser = request.getRemoteUser();
		String localName = request.getLocalName();
		Set<String> set = new HashSet<String>();
		set.add(remoteHost);
		set.add(remoteAddr);
//		set.add(remoteUser);
		set.add(localName);
		HttpSession sessionIP = request.getSession();
		sessionIP.setMaxInactiveInterval(60 * 60 *24 *7);
		sessionIP.setAttribute("setIP", set);
		Set<String> set1 = (Set<String>) sessionIP.getAttribute("setIP");
		for (String string : set1) {
			System.out.println(string);
		}
		System.out.println("=======================================================================");
    	//获取当前页面
    	long pageNo = StringToIntLong.StringToLong(request.getParameter("pageNo"), 1);
    	//获取每页显示的记录数
    	long pageSize = StringToIntLong.StringToLong(request.getParameter("pageSize"), Page.PAGE_SIZE);
    	//获取参数中的最大价格和最小价格
    	String min = request.getParameter("min");
    	String max = request.getParameter("max");
    	double minPrice = StringToIntLong.StringToDouble(min, 0.0);
    	double maxPrice = StringToIntLong.StringToDouble(max, Double.MAX_VALUE);
    	
    	//根据给定的页码和每页显示的记录数  查询出需要显示的分页信息【图书信息列表】 按价格查询
    	Page<Book> page = bookService.queryPagesByPrice(pageNo, pageSize, minPrice, maxPrice);
    	
    	String url = "BookIndexServlet?action=pageByPrice";
    	if (min != null) url = url + "&min=" + min;
    	if (max != null) url = url + "&max=" + max;
    	// 为分页设置URL
    	page.setUrl(url);
    	
    	//把分页模块数据 放到域对象中
    	request.setAttribute("page", page);
    	//转发到图书列表管理页面
    	request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    	
    }

}
