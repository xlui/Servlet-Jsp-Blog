package style.dx.java.dao;

import style.dx.java.model.User;

public interface UserDao {
	/**
	 * 新用户注册
	 * @param username 用户名
	 * @param password 密码
	 * @return true or false
	 */
	boolean register(String username, String password);

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return null or loginUser
	 */
	User login(String username, String password);
}
