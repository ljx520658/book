package com.atguigu.test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.utils.JDBCUtil;

/**
 * ^_^ 2017年1月10日 ^_^ 下午4:17:15 ^_^
 *
 */
public class TestJDBCUtil {

	@Test
	public void testJDBCUtil() {
		Connection connection = JDBCUtil.getConnection();
		System.out.println(connection);
	}
}
