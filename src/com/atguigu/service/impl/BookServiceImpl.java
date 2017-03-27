package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BaseDaoImpl1;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

/**
 * ^_^ 2017年1月16日 ^_^ 下午1:03:12 ^_^
 *
 */
public class BookServiceImpl extends BaseDaoImpl1<Book> implements BookService {

	private BookDao bookDao ;
	public BookServiceImpl() {
		bookDao = new BookDaoImpl();
	}
	@Override
	public boolean saveBook(Book book) {
		return bookDao.saveBook(book) > 0;
	}

	@Override
	public boolean updateBook(Book book) {
		return bookDao.updateBook(book) > 0;
	}

	@Override
	public Book queryBookById(int id) {
		return bookDao.queryBookById(id);
	}

	@Override
	public List<Book> queryAllBooks() {
		return bookDao.queryAllBooks();
	}

	@Override
	public boolean delBook(int id) {
		return bookDao.delBook(id) > 0;
	}
	@Override
	public Page<Book> queryPages(long pageNo, long pageSize) {
		//创建一个分页模型
		Page<Book> page = new Page<Book>();

		//设置每页显示的记录数
		page.setPageSize(pageSize);
		//执行SQL语句 求出总的记录数 并设置
		//select count(*) from t_book
		long totalCount = bookDao.queryPageTotalCount();
		page.setTotalCount(totalCount);
		//总的页码=总记录数/每页显示的记录数
		long pageTotal = totalCount / pageSize;
		if (totalCount % pageSize != 0 ) 	pageTotal++;
		page.setPageTotal(pageTotal);
		//设置当前页码
		page.setPageNo(pageNo);
		//求当前页需要显示的数据
		//select * from t_book limit begin, pageSize
		// 求begin = （pageNo -1 ） * pageSize
		long begin = (page.getPageNo() - 1) * pageSize;
		//执行SQL语句 并返回结果
		List<Book> books = bookDao.queryPageBook(begin, pageSize);
		//设置当前页需要显示的数据
		page.setList(books);
		for (Book book : books) {
			System.out.println(book.getImgPath() + book.getAuthor());
		}
		return page;
	}
	@Override
	public Page<Book> queryPagesByPrice(long pageNo, long pageSize,
			double minPrice, double maxPrice) {
		//创建一个分页模型
		Page<Book> page = new Page<Book>();

		//设置每页显示的记录数
		page.setPageSize(pageSize);
		//执行SQL语句 求出总的记录数 并设置
		//select count(*) from t_book between min and max
		long totalCount = bookDao.queryPageTotalCountByPrice(minPrice, maxPrice);
		page.setTotalCount(totalCount);
		//总的页码=总记录数/每页显示的记录数
		long pageTotal = totalCount / pageSize;
		if (totalCount % pageSize != 0 ) 	pageTotal++;
		page.setPageTotal(pageTotal);
		//设置当前页码
		page.setPageNo(pageNo);
		//求当前页需要显示的数据
		//select * from t_book limit begin, pageSize
		// 求begin = （pageNo -1 ） * pageSize
		long begin = (page.getPageNo() - 1) * pageSize;
		//执行SQL语句 并返回结果
		List<Book> books = bookDao.queryPageBookByPrice(begin, pageSize, minPrice, maxPrice);
		//设置当前页需要显示的数据
		page.setList(books);
		return page;
	}



}
