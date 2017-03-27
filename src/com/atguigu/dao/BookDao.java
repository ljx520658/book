package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Book;

/**
 * ^_^ 2017年1月16日 ^_^ 上午10:00:01 ^_^
 * 
 */
public interface BookDao {

	/**保存图书*/
	public int saveBook(Book book);
	
	/**修改图书*/
	public int updateBook(Book book);
	
	/**通过id查询图书*/
	public Book queryBookById(int id);
	
	/**查询所有图书*/
	public List<Book> queryAllBooks();
	
	/**删除图书*/
	public int  delBook(int id);

	/**分页显示图书列表 --- 查询总记录数*/
	public long queryPageTotalCount();
	
	/**分页显示图书列表 --- 查询分页数据*/
	public List<Book> queryPageBook(long begin, long pageSize);

	/**首页 分页显示图书列表 --- 查询总记录数*/
	public long queryPageTotalCountByPrice(double minPrice, double maxPrice);

	/**首页 分页显示图书列表 --- 查询分页数据*/
	public List<Book> queryPageBookByPrice(long begin, long pageSize,
			double minPrice, double maxPrice);
	
}
