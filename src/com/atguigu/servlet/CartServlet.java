package com.atguigu.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.StringToIntLong;
import com.google.gson.Gson;

/**
 * 购物车模块的 Servlet 程序
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson = new Gson();  
	private BookService bookService = new BookServiceImpl();
    public CartServlet() {
        super();
    }

    /**使用ajax 添加商品到购物车*/
    protected void ajaxAddCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过页面获取id，查询书的信息
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		Book book = bookService.queryBookById(id);
		//创建购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 如果是空，说明是还没有添加任意商品
		if (cart == null) {
			// 创建一个新的购物车
			cart = new Cart();
			//创建完一定要添加到域对象中
			request.getSession().setAttribute("cart", cart);
		}
		// 添加商品到购物车 该构造器最后一个参数可能有问题 使用的是BigDecimal  后面 + ""
		cart.addItem(new CartItem(book.getId(), book.getName(), 1,  book.getPrice(), book.getPrice()));
		//在session域对象中添加最后一个商品的名称
		request.getSession().setAttribute("last_product_name", book.getName());    	
    	
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("totalCount", cart.getTotalCount());
		map.put("last_product_name", book.getName());
		String mapJsonStr = gson.toJson(map);
		response.getWriter().write(mapJsonStr);
    }
    
    /**添加商品到购物车*/
	protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过页面获取id，查询书的信息
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		Book book = bookService.queryBookById(id);
		//创建购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 如果是空，说明是还没有添加任意商品
		if (cart == null) {
			// 创建一个新的购物车
			cart = new Cart();
			//创建完一定要添加到域对象中
			request.getSession().setAttribute("cart", cart);
		}
		// 添加商品到购物车 该构造器最后一个参数可能有问题 使用的是BigDecimal  后面 + ""
		cart.addItem(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));
		//在session域对象中添加最后一个商品的名称
		request.getSession().setAttribute("last_product_name", book.getName());
		//请求头referer是请求时浏览器地址栏的请求地址
		String url = request.getHeader("Referer");
		// 重定向回到原来的页面
		//价格搜索以后 地址为 http://192.168.10.62:8080/book/BookIndexServlet ？action
		response.sendRedirect(url);
	}

	/**清空购物车*/
	protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取购物车
		Cart cart  = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			//清空购物车中的所有内容
			cart.clear();
			int tf = 1;
		}
		
		//Referer 这个请求头，可以拿到连接所在的页面的URL
//		String url = request.getHeader("Referev");
//		response.sendRedirect(url);
	}
	
	/**使用ajax ---修改购物车中商品的数量*/
	protected void ajaxUpdateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取修改商品编号id
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		//  获取修改商品数量
		int count = StringToIntLong.StringToInt(request.getParameter("count"), 0);
		// 获取购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		BigDecimal itemTotalPrice = null;
		if (cart != null) {
			// 修改购物车中的商品数量  已经修改为返回值是商品的总金额
			itemTotalPrice = cart.updateCount(id, count);
//			System.out.println(cart);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", cart.getTotalCount());
		map.put("totalPrice", cart.getTotalPrice());
		map.put("itemTotalPrice", itemTotalPrice);
		String mapJsonStr = gson.toJson(map);
		response.getWriter().write(mapJsonStr);
		
	}
	
	
	/**修改购物车中商品的数量*/
	protected void updateCountCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		int count = StringToIntLong.StringToInt(request.getParameter("count"), 0);
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.updateCount(id, count);
			System.out.println(cart);
		}
		
		String url = request.getHeader("Referer");
		response.sendRedirect(url);
	}
	
	/**根据id删除购物车中的商品项*/
	protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取删除的id
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		// 从session对象中获取购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.deleteItem(id);
//			System.out.println(cart);
		}
		//请求头referer是请求时浏览器地址栏的请求地址 
		String url = request.getHeader("Referer");
		response.sendRedirect(url);
	}
	
	/**根据id删除购物车中的商品项*/
	protected void ajaxDeleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取删除的id
		int id = StringToIntLong.StringToInt(request.getParameter("id"), 0);
		// 从session对象中获取购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart != null) {
			cart.deleteItem(id);
//			System.out.println(cart);
		}
		BigDecimal itemTotalPrice = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", cart.getTotalCount());
		map.put("totalPrice", cart.getTotalPrice());
		map.put("itemTotalPrice", itemTotalPrice);
		String mapJsonStr = gson.toJson(map);
		response.getWriter().write(mapJsonStr);
		System.out.println(mapJsonStr);
	}

}
