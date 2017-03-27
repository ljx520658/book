package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.sun.media.sound.MidiUtils.TempoCache;

/**
 * ^_^ 2017年2月5日 ^_^ 下午3:40:12 ^_^
 * 购物车模型
 */
public class Cart implements Serializable{

	private static final long serialVersionUID = 34324234321L;

	/**购物车中商品的信息*/
	private Map<Integer, CartItem> items = new HashMap<Integer, CartItem>();
	
	public Cart() {
		super();
	}
	
	public Cart(Map<Integer, CartItem> items) {
		super();
		this.items = items;
	}
	
	/**购物车中商品的总数量*/
	public int getTotalCount() {
		int totalCount = 0;
		//遍历购物车集合中每一个商品的数量
		for(CartItem item : items.values()) {
			totalCount += item.getCount();
		}
		return totalCount;
	}
	
	/**购物车中商品的总价格*/
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal("0");
		//遍历购物车集合中每一个商品  计算总价格
		for (CartItem item : items.values()) {
			totalPrice = totalPrice.add(item.getTotalPrice());
		}
		return totalPrice;
	}
	
	/**为购物车中的商品项提供相应的getset方法*/
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}
	
	/**在购物车中添加商品*/
	public void addItem(CartItem cartItem) {
		//先从购物车中获取
		CartItem temp = items.get(cartItem.getId());
		//就说明 购物车中没有此商品
		if (temp == null) {
			items.put(cartItem.getId(), cartItem);
		} else {
			//否则 有商品 则数量加1 价格也变化
			temp.setCount(temp.getCount() + 1);
			temp.setTotalPrice(new BigDecimal(temp.getCount() * temp.getPrice().doubleValue() + ""));
		}
	}
	
	/**修改购物车中商品的数量*/
	public BigDecimal updateCount(int id, int count) {
		//先从购物车中取出商品
		CartItem temp = items.get(id);
		// 如果为空 说明之前没有此商品
		if (temp != null) {
			// 修改商品的数量和总金额
			temp.setCount(count);
			temp.setTotalPrice(new BigDecimal(temp.getCount() * temp.getPrice().doubleValue() + ""));
			return temp.getTotalPrice();
		}
		return new BigDecimal(0);
	}
	
	/**根据id移除商品项*/
	public void	deleteItem(int id) {
		items.remove(id);
	}
	
	/**清空购物车*/
	public void clear() {
		items.clear();
	}

	@Override
	public String toString() {
		return "Cart [ totalCount= " + getTotalCount() + " , totalPrice = " + getTotalPrice() + " ,items=" + items + "]";
	}
	
	
	
	/**测试BigDecimal，在java编程中，如果遇到和钱有关的数据，对数据有要求的，必须使用BigDecimal类，
	 * 像 double float 这种数据类型都是会有精度丢失的*/
	public static void main(String[] args) {
		//精度测试
		double d1 = 0.01, d2 = 0.06, d3;
		d3 = d1 + d2;
		System.out.println(d3);//正确应该是输出 0.07       计算机输出为：0.06999999999999999
		
		d1 = 0.7;
		System.out.println(d1 + d1 + d1);
		// 正常应该是输出2.1  由于精度问题输出为2.0999999999999996
		
		BigDecimal big1 = new BigDecimal(d1 + "");
		System.out.println(big1.add(new BigDecimal(d1 + "")).add(new BigDecimal(d1 + "")));
		System.out.println(big1);
		
	}
	
	
	/***/
	
	
}
