package com.atguigu.dao.impl;

import java.util.Collection;
import java.util.List;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;

/**
 * ^_^ 2017年2月6日 ^_^ 下午7:30:46 ^_^
 *
 */
public class OrderItemDaoImpl extends BaseDaoImpl1<OrderItem> implements
		OrderItemDao {

	@Override
	public void saveOrderItem(OrderItem item) {
		// 插入订单项
		String sql = "insert into t_order_item(`name`, `count`, `price`,`total_price`, `order_id`) values(?,?,?,?,?)";
		// 执行插入的数据语句
		update(sql, item.getName(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
	}

	/**使用batch 批量执行sql语句  保存订单项*/
/*	@Override
	public void batchSaveOrderItem(List<OrderItem> items) {
		// 插入订单项
		String sql = "insert into t_order_item(`name`, `count`, `price`,`total_price`, `order_id`) values(?,?,?,?,?)";
		// 所有sql语句的所有参数  共5个参数
		Object[][] params = new Object[items.size()][5];
		int i = 0;
		// 遍历每一个订单项，创建参数
		for (OrderItem item : items) {
			Object[] oneParams = params[i];
			oneParams[0] = item.getName();
			oneParams[1] = item.getCount();
			oneParams[2] = item.getPrice();
			oneParams[3] = item.getTotalPrice();
			oneParams[4] = item.getOrderId();
			i++;
		}
		
		batchUpdate(sql, params);
	}*/

	@Override
	public List<OrderItem> queryOrderDetailByOrderId(String orderId) {
		// 查询指定订单项的订单明细sql语句
		String sql = "select `id`, `name`, `count`, `price`,`total_price` totalPrice, `order_id` orderId from t_order_item where order_id = ?";
		// 执行sql语句
		return queryList(sql, orderId);
	}

	/**使用batch 批量执行sql语句  保存订单项*/
	@Override
	public void batchSaveOrderItem(List<OrderItem> items) {
		System.out.println("baseServlet 当前线程名---" + Thread.currentThread().getName());
		// 插入订单项sql语句
		String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
		// 所有sql 语句的所有参数
		Object [][] params = new Object[items.size()][];
		// 遍历所有的OrderItem 创建一次sql 语句的参数数组
		for (int i = 0; i < items.size(); i++) {
			// 创建一个一维数组
			Object [] paramsObjects = new Object[5];
			OrderItem item = items.get(i);
			paramsObjects[0] = item.getName();
			paramsObjects[1] = item.getCount();
			paramsObjects[2] = item.getPrice();
			paramsObjects[3] = item.getTotalPrice();
			paramsObjects[4] = item.getOrderId();
			// 把一个sql 数据的参数数组，赋值给所有sql语句的总参数数组
			params[i] = paramsObjects;
		}
		batchUpdate(sql, params);
	}

}
