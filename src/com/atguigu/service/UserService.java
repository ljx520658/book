package com.atguigu.service;

import com.atguigu.bean.User;

/**
 * ^_^ 2017年1月10日 ^_^ 下午8:01:00 ^_^
 * 编写业务层代码
 */
public interface UserService {
	
	/**登录*/
//	public boolean login(String username, String password);
	public User login(String username, String password);
	
	/**注册*/
	public int regist(User user);
	
	/**检查用户是否存在*/
	public User checkUser(String username);
	
	
	
}
