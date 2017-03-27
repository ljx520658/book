package com.atguigu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * ^_^ 2017年1月10日 ^_^ 下午5:55:10 ^_^
 *
 */
public interface BaseDao <T> {

	/**获取总数*/
	public Long getCount(Connection conn, String sql, Object ...args) throws SQLException;
	
	/**查询所有对象*/
	public List<T> getAll(Connection conn, String sql, Object ... args) throws SQLException;
	
	/**查询单个对象*/
	public T get(Connection conn, String sql, Object ...args) throws SQLException;
	
	/**通用的增删改*/
	public int update(Connection conn,String sql,Object ...args) throws SQLException;
}
