package style.dx.java.service;

import style.dx.java.dao.CommentDao;
import style.dx.java.dao.daoImpl.CommentDaoImplement;
import style.dx.java.model.Comment;

import java.util.List;

public class CommentService {
	private CommentDao commentDao;
	private static CommentService instance;

	private CommentService() {
		commentDao = CommentDaoImplement.getInstance();
	}

	public static CommentService getInstance() {
		if (instance == null) {
			instance = new CommentService();
		}
		return instance;
	}

	public List<Comment> loadComment(int articleId) {
		return commentDao.getComment(articleId);
	}

	public boolean addComment(Comment comment) {
		return commentDao.addComment(comment);
	}
}
