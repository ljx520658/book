package com.atguigu.test;

import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

/**
 * ^_^ 2017年2月6日 ^_^ 下午9:06:19 ^_^
 *
 */
public class OrderServiceImplTest {

	private OrderService orderService = new OrderServiceImpl();
	
	
	@Test
	public void testOrderServiceImpl() {
		fail("Not yet implemented");
	}

	//创建订单
	@Test
	public void testCreateOrder() {
		Cart cart = new Cart();
		cart.addItem(new CartItem(1, "javaWEb", 11, new BigDecimal(11+""), new BigDecimal(33)));
		cart.addItem(new CartItem(2, "javaWEb22", 22, new BigDecimal(11+""), new BigDecimal(33)));
		cart.addItem(new CartItem(1, "javaWEb", 11, new BigDecimal(11+""), new BigDecimal(33)));
		cart.addItem(new CartItem(1, "javaWEb", 11, new BigDecimal(11+""), new BigDecimal(33)));
		
		System.out.println(cart);
		
		String orderid = orderService.createOrder(cart, 1);
		System.out.println("生成的订单号：" + orderid);
	}

	// 查询我的订单
	@Test
	public void testMyOrders() {
		System.out.println("2 -->>" + orderService.myOrders(2));
		System.out.println("1 -->>" + orderService.myOrders(1));
	}

//	查看某个订单明细
	@Test
	public void testOrderDetails() {
		System.out.println(orderService.orderDetails("11486387019779"));
	}

	// 查询所有订单
	@Test
	public void testAllOrders() {
		System.out.println(orderService.allOrders());
	}

	// 发货
	@Test
	public void testSendOrder() {
		orderService.sendOrder("11486387019779");
	}

//	收货
	@Test
	public void testReceiveOrder() {
		orderService.receiveOrder("11486387019779");
	}

}
