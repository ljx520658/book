package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;

/**
 * ^_^ 2017年2月6日 ^_^ 下午7:38:34 ^_^
 *
 */
public interface OrderService {
	/** 创建订单*/
	public String createOrder(Cart cart, int userId);
	/** 查询我的订单*/
	public List<Order> myOrders(int userId);
	/** 查询某个订单的明细*/
	public List<OrderItem> orderDetails( String orderId );
	/** 查询所有订单*/
	public List<Order> allOrders();
	/** 发货*/
	public void sendOrder( String orderId ); 
	/** 收货*/
	public void receiveOrder( String orderId );
}
