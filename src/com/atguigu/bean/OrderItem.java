package com.atguigu.bean;

import java.math.BigDecimal;

/**
 * ^_^ 2017年2月6日 ^_^ 下午6:01:50 ^_^ 订单商品项 实体类
 */
public class OrderItem {

	/** 订单项编号 */
	private int id;
	/** 商品名称 */
	private String name;
	/** 商品数量 */
	private int count;
	/** 商品价格 */
	private double price;
	/** 订单项商品总价格 */
	private BigDecimal totalPrice;
	/** 属于某个订单号 */
	private String orderId;

	public OrderItem() {
		super();
	}

	public OrderItem(int id, String name, int count, double price,
			BigDecimal totalPrice, String orderId) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.totalPrice = totalPrice;
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", name=" + name + ", count=" + count
				+ ", price=" + price + ", totalPrice=" + totalPrice
				+ ", orderId=" + orderId + "]";
	}

}
