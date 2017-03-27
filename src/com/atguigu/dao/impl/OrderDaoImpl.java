package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Order;
import com.atguigu.dao.OrderDao;

/**
 * ^_^ 2017年2月6日 ^_^ 下午7:14:52 ^_^
 *
 */
public class OrderDaoImpl extends BaseDaoImpl1<Order> implements OrderDao {


	@Override
	public void saveOrder(Order order) {
		// 插入订单的sql
		String sql = "insert into t_order(`order_id`,`create_time`, `price`,`status`,`user_id`) values(?,?,?,?,?)";
		// 执行插入操作
		update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
	}

	@Override
	public List<Order> queryMyOrders(int userId) {
		// 查询我的订单的sql语句
		String sql = "select `order_id` orderId ,`create_time` createTime , `price`,`status`,`user_id` userId from t_order where user_id = ?";
		// 执行查询 操作
		return  queryList(sql, userId);
	}

	@Override
	public List<Order> queryAllOrders() {
		// 查询所有订单的sql语句
		String sql = "select `order_id` orderId ,`create_time` createTime , `price`,`status`,`user_id` userId from t_order";
		// 执行查询 操作
		return  queryList(sql);
	}

	@Override
	public void changeOrderStatus(int status, String orderId) {
		// 修改订单状态sql语句
		String sql = "update t_order set status = ? where order_id = ?";
		// 执行sql语句
		update(sql, status,orderId);
	}

}
