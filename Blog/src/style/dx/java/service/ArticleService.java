package style.dx.java.service;

import style.dx.java.dao.ArticleDao;
import style.dx.java.daoImpl.ArticleDaoImplement;
import style.dx.java.model.Article;

import java.util.List;

public class ArticleService {
	private ArticleDao articleDao;
	private static ArticleService instance;

	private ArticleService() {
		articleDao = ArticleDaoImplement.getInstance();
	}

	public static ArticleService getInstance() {
		if (instance == null) {
			instance = new ArticleService();
		}
		return instance;
	}

	public List getArticle() {
		return articleDao.getAllArticle();
	}

	public List<Article> getArticle(String column, String value) {
		return articleDao.getArticleByColumn(column, value);
	}

	public Article getPreviousArticle(String time) {
		return articleDao.getLastArticle(time);
	}

	public Article getNextArticle(String time) {
		return articleDao.getNextArticle(time);
	}


}
