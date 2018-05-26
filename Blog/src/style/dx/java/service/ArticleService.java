package style.dx.java.service;

import style.dx.java.dao.ArticleDao;
import style.dx.java.dao.daoImpl.ArticleDaoImpl;
import style.dx.java.model.Article;
import style.dx.java.util.ArticleUtils;
import style.dx.java.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {
	private ArticleDao articleDao;

	private ArticleService() {
		articleDao = ArticleDaoImpl.getInstance();
	}

	public static ArticleService getInstance() {
		return Inner.articleService;
	}

	private static final class Inner {
		private static ArticleService articleService = new ArticleService();
	}

	/**
	 * 获取数据库中所有文章，在返回前对文章格式进行处理
	 * @return 文章列表
	 */
	public List getArticle() {
		List<Article> articles = articleDao.getArticles();
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
		return articleDao.count(searchKey);
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
			List sorts = articleDao.getSorts();
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
