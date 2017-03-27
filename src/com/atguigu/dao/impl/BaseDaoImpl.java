 package com.atguigu.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.dao.BaseDao;
import com.atguigu.utils.ReflectionUtils;

/**
 * ^_^ 2017年1月10日 ^_^ 下午6:00:14 ^_^
 * 代码兼容性不好，不建议使用
 */
public abstract class BaseDaoImpl <T> implements BaseDao<T> {
	
	private QueryRunner qr = new QueryRunner();
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		clazz = ReflectionUtils.getSuperGenericType(this.clazz);
	}

	/**获取总数*/
	@Override
	public Long getCount(Connection conn, String sql, Object... args)
			throws SQLException {
		return (Long) qr.query(conn, sql, new ScalarHandler(), args);
	}

	/**查询所有对象*/
	@Override
	public List<T> getAll(Connection conn, String sql, Object... args)
			throws SQLException {
		return qr.query(conn, sql, new BeanListHandler<>(clazz), args);
	}

	/**查询单个对象*/
	@Override
	public T get(Connection conn, String sql, Object... args)
			throws SQLException {
		return qr.query(conn, sql, new BeanHandler<>(clazz), args);
	}

	/**通用的增删改*/
	@Override
	public int update(Connection conn, String sql, Object... args)
			throws SQLException {
		return qr.update(conn, sql, args);
	}

}
