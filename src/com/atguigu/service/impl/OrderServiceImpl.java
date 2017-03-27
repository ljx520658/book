package com.atguigu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.service.OrderService;
import com.atguigu.utils.MYUUID;

/**
 * ^_^ 2017年2月6日 ^_^ 下午7:43:16 ^_^
 *
 */
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;
	private OrderItemDao orderItemDao;
	private BookDao bookDao;

	public OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
		orderItemDao = new OrderItemDaoImpl();
		bookDao = new BookDaoImpl();
	}
	
	@Override
	public String createOrder(Cart cart, int userId) {
		// 订单号 使用 当前时间 + 用户Id 以后改为UUID自定义时间
//		String orderId = userId + "" + System.currentTimeMillis();
		String orderId = MYUUID.ID() + userId;
		// 生成一个订单对象 新生成的订单状态为 未发货
		Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
		// 保存订单
		orderDao.saveOrder(order);
//		int i = 12/0;
		List<OrderItem> items = new ArrayList<OrderItem>();
		// 遍历所有的购物车中的商品，生成订单项
		 for (CartItem cartItem : cart.getItems().values()) {
			// 查找出 结账的图书
			 Book book = bookDao.queryBookById(cartItem.getId());
			 // 修改销量
			 book.setSales(book.getSales() + cartItem.getCount());
			 // 修改库存
			 book.setStock(book.getStock() - cartItem.getCount());
			 bookDao.updateBook(book);
			 
			 // 根据购物车中的商品项，生成订单项
			 OrderItem orderItem = new OrderItem(0, cartItem.getName(), cartItem.getCount(), cartItem.getPrice().doubleValue(), cartItem.getTotalPrice(), orderId);
			 
			 // 保存订单项  一次保存一个订单项 执行一次sql 语句 访问一个数据库  性能低
//			 orderItemDao.saveOrderItem(orderItem);
			 // 把所有的OrderItem都保存到集合中，然后批量插入到数据库中 使用批量 性能高
			 items.add(orderItem);
		}
		 // 一次批量插入多条记录到数据库
		 orderItemDao.batchSaveOrderItem(items);
		 
		 // 清空购物车
		 cart.clear();
		 // 返回订单号
		 return orderId;
	}

	@Override
	public List<Order> myOrders(int userId) {
		return orderDao.queryMyOrders(userId);
	}

	@Override
	public List<OrderItem> orderDetails(String orderId) {
		return orderItemDao.queryOrderDetailByOrderId(orderId);
	}

	@Override
	public List<Order> allOrders() {
		return orderDao.queryAllOrders();
	}

	@Override
	public void sendOrder(String orderId) {
		orderDao.changeOrderStatus(1, orderId);
	}

	@Override
	public void receiveOrder(String orderId) {
		orderDao.changeOrderStatus(2, orderId);
	}

}
