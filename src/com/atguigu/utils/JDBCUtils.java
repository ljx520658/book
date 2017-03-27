package com.atguigu.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * dabao 2016年12月21日 上午11:50:57
 *   该程序兼容性不好
*/
public class JDBCUtils {

	private static DataSource ds = null;
	
	static{
		ds = new ComboPooledDataSource("book_devoloper");
	}
	
	/**使用c3p0 数据库连接池获取连接*/
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	/**关闭连接*/
	public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/***/
}
