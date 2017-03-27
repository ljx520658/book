package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;

/**
 * ^_^ 2017年1月10日 ^_^ 下午4:31:48 ^_^
 *
 */
public class UserDaoImpl extends BaseDaoImpl1<User> implements UserDao {

	/**用户登录*/
	@Override
	public User login(String username, String pwd) {
		//根据用户名密码 查询用户是否存在
		String sql = "select id,username,password,email from t_user where username = ? and password = ?";
		User user = queryBean(sql, username,pwd);
		return user; 
	}

	/**用户注册 注册成功 返回值为int 应影响多少行*/
	@Override
	public int saveUser(User user) {
		String sql = "insert into t_user(id,username,password,email) values(null,?,?,?)";
		//执行增删改 并返回 以影响行数
		return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
	}

	/**根据用户名查询用户是否存在*/
	@Override
	public User checkUser(String username) {
		String sql = "select username from t_user where username = ?";
		return queryBean(sql, username);
	}

}
