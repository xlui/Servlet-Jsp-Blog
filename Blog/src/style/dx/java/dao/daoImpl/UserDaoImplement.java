package style.dx.java.dao.daoImpl;

import style.dx.java.dao.UserDao;
import style.dx.java.model.User;
import style.dx.java.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImplement implements UserDao {
	private Connection connection = null;
	private static UserDaoImplement instance;

	private UserDaoImplement() {
		connection = DBUtils.getInstance();
	}

	public static UserDaoImplement getInstance() {
		if (instance == null) {
			instance = new UserDaoImplement();
		}
		return instance;
	}

	@Override
	public boolean register(String username, String password) {
		// todo: user register
		return false;
	}

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录的用户或者 null
	 */
	@Override
	public User login(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
