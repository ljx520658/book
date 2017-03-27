package com.atguigu.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JDBCUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * ^_^ 2017年1月10日 ^_^ 下午4:35:04 ^_^
 * 该项目中以本文件有效 这个代码时正确的 <br/>
 * 由于后期修改了代码，考虑到事务的原子性，故代码有所改变 ,
 * 注意在BaseDaoImpl中，都不要在关闭Connection，因为要让所有的操作都在同一个Connection中使用，而且所有的异常都要往外抛，
 * 直到让Filter可以拦截到，做rollback回滚操作。
 */
public class BaseDaoImpl1 <T>{

	//使用JDBCUtils 
	private QueryRunner queryRunner;
	
	//使用泛型的类型 用于保存BaseDaoImpl类的泛型对象 class 类型
	private Class<T> type;
	
	//构造器初始化
	@SuppressWarnings("unchecked")
	public BaseDaoImpl1 (){
		// 创建一个QueryRunner 对象实例
		queryRunner = new QueryRunner();
		//获取父类中带有泛型的父类的class类型
		ParameterizedType superType = (ParameterizedType) getClass().getGenericSuperclass();
		//获取泛型中的具体的类型的class
		type = (Class<T>) superType.getActualTypeArguments()[0];
//		System.out.println(type);
	}
	
	/**查询返回的集合*/
	public List<T> queryList(String sql, Object ...args) {
		List<T> list =null;  // 此处也可以不用定义，直接return 即可，如下面的T t = null;
		//获取数据库连接对象
		Connection conn = null;
		
		try {
			// 获取数据库连接
			conn = JDBCUtil.getConnection();
			// 执行查询操作
			list = queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
		} catch (SQLException e) {//可能是 Exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} /*finally {
			//关闭连接  考虑到事务的原子性，已经做了修改，制造并抛出异常
			JDBCUtil.closeConnection(conn);
		}*/
		return list;
	}
	
	/**查询单个记录*/
	public T queryBean(String sql, Object ...args) {
//		T t = null; //此处省略，直接return 和上面的list 对比
		// 创建数据库连接对象
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = JDBCUtil.getConnection();
			// 执行查询操作
			return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
		} catch (SQLException e) { //可能是 Exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} /*finally {
			JDBCUtil.closeConnection(conn);
		}*/
		
	}
	
	/**使用batch 批量执行SQL语句 */
	public int [] batchUpdate(String sql, Object [][] params) {
		Connection connection = null;
		try {
			// 获取数据库连接
			connection = JDBCUtil.getConnection();
			// 执行批量的sql 语句
			return queryRunner.batch(connection, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**增删改操作*/
	public int update(String sql, Object ...args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			//执行SQL语句 并返回结果
			return queryRunner.update(conn,sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} /*finally {
			JDBCUtil.closeConnection(conn);
		}
		return 0;*/
	}
	
	/**执行只需要返回一个列值的语句 */
	public Object queryForSingleValue(String sql, Object ...params) {
		Object result = null;
		Connection conn = null;
		
		try {
			conn = JDBCUtil.getConnection();
			result = queryRunner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} /*finally {
			JDBCUtil.closeConnection(conn);
		}*/
		return result;
	}

	
}
