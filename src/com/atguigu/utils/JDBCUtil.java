package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ^_^ 2017年1月10日 ^_^ 上午11:35:22 ^_^
 * c3p0数据库连接池技术  
 * 该项目中以本文件有效 这个代码时正确的
 * 后期使用Filter 和 ThreadLocal 组合来控制事务 
 * 主要用于保存订单时 （有三件事要做，保存订单表，订单项表，修改商品销量及库存） 要么都成功要么都失败，事务回滚到提交之前
 * 使用ThreadLocal类可以让我们在每个线程中存取自己的一个数据的引用，而其他的线程无法访问和操作别人线程中的数据，这样可以有效的确保数据的安全性 
 */
public class JDBCUtil {
	
	//创建c3p0数据库连接对象
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("book_devoloper");
	
	// 使用ThreadLocal 保证在同一个线程中，使用的是同一个Connection对象
	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();
	
	private JDBCUtil() {}
	
	/** 如果获取数据库连接成功，返回数据库的连接对象。<br/> 如果获取数据库连接失败，则返回null*/
	public static Connection getConnection() {
		// 先从ThreadLocal中获取数据库连接对象
		Connection connection = connThreadLocal.get();
		try {
			if (connection == null) {
				// 从c3p0 连接池获取连接
				connection = dataSource.getConnection();
				connThreadLocal.set(connection);
				// 设置当前数据库连接的事务， 要手动提交
				connection.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void commitAndCloseConnection() {
		// 从ThreadLocal 中获取当前线程的连接
		Connection conn = connThreadLocal.get();
		if (conn != null) {
			try {
				// 提交事务
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// 关闭连接
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 一定要调用remove 方法移除当前线程的connection
		connThreadLocal.remove();
	}
	
	public static void rollbackAndCloseConnection() {
		// 从ThreadLocal 中获取当前线程的连接
		Connection conn = connThreadLocal.get();
		if (conn != null) {
			try {
				// 回滚事务
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 关闭连接
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 一定要调用remove 方法移除 当前线程的connection
		connThreadLocal.remove();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.println(JDBCUtil.getConnection());
		}
	}
	
	
/*	//获取数据库连接  该方法不推荐使用， 没有考虑到事务的原子性
	public static Connection getConnection() {
		Connection conn = null;
		//从c3p0连接池获取数据库连接
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭数据库连接
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/

}
