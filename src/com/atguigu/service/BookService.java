package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

/**
 * ^_^ 2017年1月16日 ^_^ 下午12:58:54 ^_^
 *
 */
public interface BookService {

	/**保存图书*/
	public boolean saveBook(Book book);
	
	/**修改图书*/
	public boolean updateBook(Book book);
	
	/**通过id查询图书*/
	public Book queryBookById(int id);
	
	/**查询所有图书*/
	public List<Book> queryAllBooks();
	
	/**删除图书*/
	public boolean  delBook(int id);

	/**分页显示图书列表*/
	public Page<Book> queryPages(long pageNo, long pageSize);

	/**首页 按价格 分页显示图书信息 */
	public Page<Book> queryPagesByPrice(long pageNo, long pageSize,
			double minPrice, double maxPrice);
	
	
	
	
	
	
}
