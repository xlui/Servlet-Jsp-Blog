package style.dx.java.service;

import style.dx.java.dao.TagDao;
import style.dx.java.dao.UserDao;
import style.dx.java.daoImpl.TagDaoImplement;
import style.dx.java.daoImpl.UserDaoImplement;
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
