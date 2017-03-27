package com.atguigu.bean;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * ^_^ 2017年2月5日 ^_^ 下午4:59:52 ^_^
 *
 */
public class CartTest {

	Cart cart = new Cart();
	public CartTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testGetTotalCount() {
		
		
		
	}

	@Test
	public void testGetTotalPrice() {
		
		
	}

	@Test
	public void testAddItem() {

		cart.addItem(new CartItem(1, "新年新气象", 1,  new BigDecimal(33+""), new BigDecimal(33+"")));
		cart.addItem(new CartItem(2, "吉祥如意", 2,  new BigDecimal(11+""), new BigDecimal(11+"")));
		cart.addItem(new CartItem(3, "金鸡报喜", 5,  new BigDecimal(11+""), new BigDecimal(22+"")));
		cart.addItem(new CartItem(1, "新年新气象", 1,  new BigDecimal(11+""), new BigDecimal(33+"")));
		
		System.out.println(cart);
	}

	@Test
	public void testUpdateCount() {
		//调用添加购物车
		testAddItem();
		//设置id为2 的数量为6  修改后的数量结果为 1 6 5 1  即13
		cart.updateCount(2, 6);
		System.out.println(cart);
		
	}

	@Test
	public void testDeleteItem() {
		//调用添加购物车
		testAddItem();
		cart.deleteItem(3);
		System.out.println(cart);
	}

	@Test
	public void testClear() {
		testAddItem();
		cart.clear();
		System.out.println(cart);
		
	}

}
