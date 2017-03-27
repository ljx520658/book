package com.atguigu.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;

/**
 * ^_^ 2017年1月16日 ^_^ 上午11:15:50 ^_^
 *
 */
public class BookDaoImplTest {

	BookDao bookDao=new BookDaoImpl();
	@Test
	public void testSaveBook() {
		System.out.println(bookDao.saveBook(new Book(0, "ddd", "dddffd", new BigDecimal(11+""), 34, 343, "")));;
	}

	@Test
	public void testUpdateBook() {
		System.out.println(bookDao.updateBook(new Book(20, "ww", "qq", new BigDecimal(11+""), 11, 22, "")));
	}

	@Test
	public void testQueryBookById() {
		System.out.println(bookDao.queryBookById(11));
	}

	@Test
	public void testQueryAllBooks() {
		System.out.println(bookDao.queryAllBooks().size());;
	}

	@Test
	public void testDelBook() {
		System.out.println(bookDao.delBook(20));;
	}

}
