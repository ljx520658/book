package com.atguigu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

/**
 * ^_^ 2017年1月18日 ^_^ 下午9:59:23 ^_^
 *
 */
public class BookServiceImplTest {
	private BookService bookService = new BookServiceImpl();
	private BookDao bookDao = new BookDaoImpl();

	@Test
	public void testBookServiceImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryBookById() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryAllBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryPages() {
		long totalCount = bookDao.queryPageTotalCountByPrice(0, 2000);
//		System.out.println(totalCount);
		Page<Book> page = bookService.queryPagesByPrice(1, 4, 11, 20);
		
//		System.out.println(page);
		List<Book> list = page.getList();
		for (Book book : list) {
			System.out.println(book);
			System.out.println(book.getImgPath());
		}
	}

	@Test
	public void testQueryPagesByPrice() {
		fail("Not yet implemented");
	}

}
