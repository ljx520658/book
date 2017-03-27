package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.dao.BookDao;

/**
 * ^_^ 2017年1月16日 ^_^ 上午10:01:35 ^_^
 */
public class BookDaoImpl extends BaseDaoImpl1<Book> implements BookDao{
	
	@Override
	public int saveBook(Book book) {
		String sql = "insert into t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) values(null,?,?,?,?,?,?) ";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
	}

	@Override
	public int updateBook(Book book) {
		String sql = "update t_book set name = ? ,author = ? ,price = ? ,sales = ? ,stock = ? , img_path = ? where id = ?";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
	}

	@Override
	public Book queryBookById(int id) {
		String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book where id=?";
		return queryBean(sql, id);
	}

	@Override
	public List<Book> queryAllBooks() {
		String sql = "select id,name,author,price,sales,stock,img_path imgPath from t_book";
		return queryList(sql);
	}

	@Override
	public int delBook(int id) {
		String sql = "delete from t_book where id=?";
		return update(sql, id);
	}

	@Override
	public long queryPageTotalCount() {
		String sql = "select count(*) from t_book"; 
		//执行SQL 
		Object resultObj = queryForSingleValue(sql);
		// 把数据返回给service
		return Long.parseLong(resultObj.toString());
	}

	@Override
	public List<Book> queryPageBook(long begin, long pageSize) {
		String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
		return queryList(sql, begin, pageSize);
	}

	@Override
	public long queryPageTotalCountByPrice(double minPrice, double maxPrice) {
		String sql = "select count(*) from t_book where price between ? and ?";
		Object resultObj = queryForSingleValue(sql,minPrice, maxPrice);
		return Long.parseLong(resultObj.toString());
	}

	@Override
	public List<Book> queryPageBookByPrice(long begin, long pageSize,
			double minPrice, double maxPrice) {
		String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where price between ? and ? limit ?,?";
		return queryList(sql, minPrice, maxPrice, begin, pageSize);
	}

}
