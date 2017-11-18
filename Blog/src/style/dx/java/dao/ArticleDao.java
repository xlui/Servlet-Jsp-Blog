package style.dx.java.dao;

import style.dx.java.model.Article;

import java.util.List;

public interface ArticleDao {
	// 查询文章或者分类时参数对应的字段名
	public static final String SEARCH_ARTICLE = "article";
	public static final String SEARCH_SORT = "sort";

	/**
	 * 通过列与值获得文章
	 * @param column 列名
	 * @param value 值
	 * @return 文章列表
	 */
	List<Article> getArticleByColumn(String column, String value);

	/**
	 * 获取所有文章
	 * @return `List` of articles
	 */
	List<Article> getAllArticle();

	/**
	 * 获取最新的文章
	 * @return 文章对象
	 */
	Article getLatestArticle();

	/**
	 * 获取上一篇文章，基于时间判断
	 * @param time 当前文章的时间
	 * @return 上一篇文章对象
	 */
	Article getLastArticle(String time);

	/**
	 * 获取下一篇文章，基于时间判断
	 * @param time 当前文章的时间
	 * @return 下一篇文章对象
	 */
	Article getNextArticle(String time);

	/**
	 * 添加新文章
	 * @param article 新的文章对象
	 * @return 成功添加的文章对象
	 */
	Article addArticle(Article article);

	/**
	 * 删除一篇文章
	 * @param id 文章 id
	 * @return 成功 true，失败 false
	 */
	Boolean deleteArticle(Integer id);

	/**
	 * 查询总数量，可以是文章也可以是分类
	 * @param search_key 查询的 key，已经被定义为类的静态变量
	 * @return 数量
	 */
	int getCount(String search_key);

	/**
	 * 所有的分类
	 * @return 分类列表
	 */
	List getAllSort();

	/**
	 * 更新分类
	 * @param oldSort 原分类
	 * @param newSort 新分类
	 * @return 成功 true，失败 false
	 */
	Boolean updateSort(String oldSort, String newSort);

	/**
	 * 删除分类
	 * @param sort 分类名
	 * @return 成功是否
	 */
	Boolean deleteSort(String sort);
}
