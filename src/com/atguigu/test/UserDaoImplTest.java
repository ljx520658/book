package com.atguigu.test;

import org.junit.Test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

/**
 * ^_^ 2017年1月10日 ^_^ 下午7:39:28 ^_^
 *
 */
public class UserDaoImplTest {
	UserDao userDao = new UserDaoImpl();

	
	@Test
	public void testLogin() {
		User user = userDao.login("admin", "admin");
		System.out.println(user);
		
		User user1 = userDao.login("admin11", "admin11");
		System.out.println(user1);
	}

	@Test
	public void testSaveUser() {
		User user = new User(3, "success", "succ", "succes@188.com");
		int saveUser = userDao.saveUser(user);
		System.out.println(saveUser);
	}

	@Test
	//测试用户名是否存在
	public void testCheckUser() {
		User checkUser = userDao.checkUser("admin");
		System.out.println(checkUser.getUsername());
	}

}
