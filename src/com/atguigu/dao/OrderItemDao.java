package com.atguigu.dao;

import java.util.Collection;
import java.util.List;

import com.atguigu.bean.OrderItem;

/**
 * ^_^ 2017年2月6日 ^_^ 下午7:28:12 ^_^
 *
 */
public interface OrderItemDao {
	/** 保存订单项*/
	public void saveOrderItem(OrderItem item);
	/** 根据订单号，查询订单项*/
	public List<OrderItem> queryOrderDetailByOrderId(String orderId);
	/**使用batch 批量执行sql语句 保存订单项  一次插入多个订单项*/
	public void batchSaveOrderItem(List<OrderItem> items);
}
