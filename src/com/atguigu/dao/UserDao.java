package com.atguigu.dao;

import com.atguigu.bean.User;

/**
 * ^_^ 2017年1月10日 ^_^ 下午4:24:01 ^_^
 * UserDao 是User对象和t_user 表之间的操作，如 增、删、改、查
 */
public interface UserDao {

	/**用户登录*/
	public User login(String username, String pwd);
	
	/**用户注册 注册成功 返回值为int 应影响多少行*/
	public int	saveUser(User user);
	
	/**根据用户名查询用户是否存在*/
	public User checkUser(String username);

	
}