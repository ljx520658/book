package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ^_^ 2017年2月6日 ^_^ 下午6:00:51 ^_^ 订单模型 实体类
 */
public class Order {

	/** 唯一订单号 */
	private String orderId;
	/** 订单生成时间 */
	private Date createTime;
	/** 订单总价 */
	private BigDecimal price;
	/** 订单状态 0未发货 1已发货 2已签收 */
	private int status;
	/** 用户编号 */
	private int userId;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, Date createTime, BigDecimal price, int status,
			int userId) {
		super();
		this.orderId = orderId;
		this.createTime = createTime;
		this.price = price;
		this.status = status;
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", createTime=" + createTime
				+ ", price=" + price + ", status=" + status + ", userId="
				+ userId + "]";
	}

}
