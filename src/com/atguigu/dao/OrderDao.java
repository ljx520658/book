package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Order;

/**
 * ^_^ 2017年2月6日 ^_^ 下午6:29:51 ^_^
 *
 */
public interface OrderDao {

	/**保存订单*/
	public void saveOrder(Order order);
	
	/**查询我的订单*/
	public List<Order> queryMyOrders(int userId);
	
	/**查询所有订单*/
	public List<Order> queryAllOrders();
	
	/**修改订单状态*/
	public void changeOrderStatus(int status, String orderId);
	
	
	/***/
	
	
	
	/***/
	
	
}
