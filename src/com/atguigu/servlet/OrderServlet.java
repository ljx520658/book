package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 146545764563L;
    
	private OrderService orderService = new OrderServiceImpl();
    public OrderServlet() {
        super();
    }
    
    /** 确认收货	 */
    protected void orderDetails(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// 获取发货的订单号
    	String orderId = request.getParameter("orderId");
    	List<OrderItem> orderDetails = orderService.orderDetails(orderId);
    	request.setAttribute("orderDetails", orderDetails);
    	request.getRequestDispatcher("/pages/order/Details.jsp").forward(request, response);
    	
    }

	/** 确认收货	 */
	protected void receiveOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取发货的订单号
		String orderId = request.getParameter("orderId");
		// 发货
		orderService.receiveOrder(orderId);
		// 获取浏览器地址当前地址
		String url = request.getHeader("referer");
		// 重定向回我的订单管理 页面
		response.sendRedirect(url);
	}
	
	/**
	 * 确认发货
	 */
	protected void sendOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取发货的订单号
		String orderId = request.getParameter("orderId");
		// 发货
		orderService.sendOrder(orderId);
		// 获取浏览器地址当前地址
		String url = request.getHeader("referer");
		// 重定向回订单管理 页面
		response.sendRedirect(url);
	}

	/**
	 * 管理员查看 所有定单
	 */
	protected void allOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查询所有的订单信息
		List<Order> allOrders = orderService.allOrders();
		// 把查询 到的所有订单信息，保存到request域对象中
		request.setAttribute("allOrders", allOrders);
		// 转发到管理员订单页面。
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}

	/**
	 * 查询我的订单列表
	 */
	protected void myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先获取当前用户。
		User user = (User) request.getSession().getAttribute("logUser");
		// 如果user对象为null说明，还没有登录。
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
			return;
		}
		// 获取用户的id
		int userId = user.getId();
		// 查询出当前用户的所有订单信息。
		List<Order> myOrders = orderService.myOrders(userId);
		// 把查询 出来订单信息，保存到域对象中
		request.setAttribute("myOrders", myOrders);
		// 转发到我的订单页面
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	}

	/**
	 * 生成订单---结账
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取生成订单需要的参数，1购物车，用户id号
		User user = (User) request.getSession().getAttribute("logUser");
		// 如果user对象为null说明，还没有登录。
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
			return;
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		// 生成订单
		String orderId = orderService.createOrder(cart, user.getId());
		// 把订单编号保存到request域对象中，用于显示
		request.setAttribute("orderId", orderId);
		// 转发到结算页面
		request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
	}

}

