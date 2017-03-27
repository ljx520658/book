package com.atguigu.bean;

import java.math.BigDecimal;

/**
 * ^_^ 2017年1月16日 ^_^ 上午9:56:00 ^_^
 *
 */
public class Book {

	// `id` INT(11) 主键
	private int id;

	// `name 书名
	private String name;
	// `author 作者
	private String author;
	// `price 价格
	private BigDecimal price;
	// `sales 销量
	private int sales;
	// `stock 库存
	private int stock;
	// `imgPath 书的图片路径
	private String imgPath="static/img/default.jpg";

	public Book() {
		super();
	}

	public Book(int id, String name, String author, BigDecimal price, int sales,
			int stock, String imgPath) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
		if (!(imgPath == null || "".equals(imgPath))) {
			this.imgPath = imgPath;
		}
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getStock() {
		if (stock < 0) {
			return 0;
		}
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}



	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		if (!(imgPath == null || "".equals(imgPath))) 
			this.imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author
				+ ", price=" + price + ", sales=" + sales + ", stock=" + stock
				+ ", imgPath=" + imgPath + "]";
	}

}
