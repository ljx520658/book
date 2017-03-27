package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

/**
 * ^_^ 2017年1月10日 ^_^ 下午8:25:42 ^_^
 *
 */
public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	
	@Override
	public User login(String username, String pwd) {
//		User user = userDao.login(username, pwd);
//		if (user != null) {
//			return true;
//		} 
		return userDao.login(username, pwd);
	}
	 
	@Override
	public int regist(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User checkUser(String username) {
		return userDao.checkUser(username);
	}


}
