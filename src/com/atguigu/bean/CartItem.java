package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ^_^ 2017年2月5日 ^_^ 下午3:32:10 ^_^
 * 购物车中的商品项
 */
public class CartItem implements Serializable {

	private static final long serialVersionUID = 14543532487732L;

	/** 商品id编号 */
	private int id;
	/** 商品的名称 */
	private String name;
	/** 商品的数量 */
	private int count = 1;
	/** 商品的单价 */
	private BigDecimal price;
	/** 商品的总价 */
	private BigDecimal totalPrice;

	public CartItem() {
		super();
	}

	public CartItem(int id, String name, int count, BigDecimal price,
			BigDecimal totalPrice) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.price = price;
		this.totalPrice = totalPrice;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", name=" + name + ", count=" + count
				+ ", price=" + price + ", totalPrice=" + totalPrice + "]";
	}

}
