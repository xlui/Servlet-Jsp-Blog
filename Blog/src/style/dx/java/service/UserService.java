package style.dx.java.service;

import style.dx.java.dao.UserDao;
import style.dx.java.dao.daoImpl.UserDaoImplement;
import style.dx.java.model.User;

public class UserService {
	private UserDao userDao;
	private static UserService instance;

	private UserService() {
		userDao = UserDaoImplement.getInstance();
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public User login(String username, String password) {
		return userDao.login(username, password);
	}
}
