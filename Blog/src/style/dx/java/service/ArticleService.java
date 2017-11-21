package style.dx.java.service;

import style.dx.java.dao.ArticleDao;
import style.dx.java.daoImpl.ArticleDaoImplement;
import style.dx.java.model.Article;
import style.dx.java.utils.ArticleUtils;
import style.dx.java.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {
	private ArticleDao articleDao;
	private static ArticleService instance;

	private ArticleService() {
		articleDao = ArticleDaoImplement.getInstance();
	}

	/**
	 * 获取单例
	 * @return 唯一 Service 实例
	 */
	public static ArticleService getInstance() {
		if (instance == null) {
			instance = new ArticleService();
		}
		return instance;
	}

	/**
	 * 获取数据库中所有文章，在返回前对文章格式进行处理
	 * @return 文章列表
	 */
	public List getArticle() {
		List<Article> articles = articleDao.getAllArticle();
		ArticleUtils.cutTime(articles);
		ArticleUtils.cutContent(articles);
		return articles;
	}

	/**
	 * 通过列名与值获取文章
	 * @param column 列名
	 * @param value 列值
	 * @return 文章对象列表
	 */
	public List<Article> getArticle(String column, String value) {
		return articleDao.getArticleByColumn(column, value);
	}

	/**
	 * 获取上一篇文章
	 * @param id 当前文章的 id
	 * @return 上一篇文章对象
	 */
	public Article getPreviousArticle(Integer id) {
		return articleDao.getLastArticle(id);
	}

	/**
	 * 获取下一篇文章
	 * @param id 当前文章的 id
	 * @return 下一篇文章对象
	 */
	public Article getNextArticle(Integer id) {
		return articleDao.getNextArticle(id);
	}

	/**
	 * 获取文章或者分类的数量
	 * @param searchKey 搜索的键
	 * @return 数量
	 */
	public int getCount(String searchKey) {
		return articleDao.getCount(searchKey);
	}

	/**
	 * 获取分类和文章键值对
	 * @param sortName 分类名
	 * @return 分类-文章键值对
	 */
	public Map getSort(String sortName) {
		Map map = new HashMap();
		List<Article> articles = null;

		if (sortName.equals("all") || StringUtils.isEmpty(sortName)) {
			List sorts = articleDao.getAllSort();
			for (Object sort : sorts) {
				articles = articleDao.getArticleByColumn("sort", (String)sort);
				ArticleUtils.cutContent(articles);
				map.put(sort, articles);
			}
		} else {
			articles = articleDao.getArticleByColumn("sort", sortName);
			ArticleUtils.cutTime(articles);
			map.put(sortName, articles);
		}
		return map;
	}
}
