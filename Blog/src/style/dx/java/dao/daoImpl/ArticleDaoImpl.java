package style.dx.java.dao.daoImpl;

import style.dx.java.dao.ArticleDao;
import style.dx.java.model.Article;
import style.dx.java.util.ArticleUtils;
import style.dx.java.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
	private Connection connection = null;

	private ArticleDaoImpl(){
		connection = DBUtils.getInstance();
	}

	public static ArticleDao getInstance() {
		return Inner.articleDao;
	}

	private static final class Inner {
		private static final ArticleDao articleDao = new ArticleDaoImpl();
	}

	/**
	 * 从 ResultSet 对象中获取一个 Article 对象
	 * @param resultSet 结果集
	 * @return 文章对象
	 * @throws SQLException SQL操作过程异常
	 */
	private Article getNewArticle(ResultSet resultSet) throws SQLException {
		return new Article(resultSet.getInt("id"), resultSet.getString("title"),
				resultSet.getString("author"), resultSet.getString("sort"), resultSet.getString("time"),
				resultSet.getString("content"), resultSet.getInt("comment"));
	}

	/**
	 * 执行 SQL 语句获取数据库中下一篇文章
	 * @param sql 查询语句
	 * @return 文章对象
	 */
	private Article getOneArticleBySQL(String sql) {
		Article article = null;
		ResultSet resultSet;
		try {
			resultSet = DBUtils.executeQuery(connection, sql);
			if (resultSet.next()) {
				article = getNewArticle(resultSet);
			}
			DBUtils.close(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return article;
	}

	/**
	 * 通过列名与值获得数据的实现
	 * @param column 列名
	 * @param value 值
	 * @return 文章对象列表
	 */
	@Override
	public List<Article> getArticleByColumn(String column, String value) {
		List<Article> articleList = null;
		String sql = "select * from article where " + column + "=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, value);
			ResultSet resultSet = preparedStatement.executeQuery();

			articleList = new ArrayList<>();
			Article article = null;
			while (resultSet.next()) {
				article = getNewArticle(resultSet);
				articleList.add(article);
			}
			DBUtils.close(preparedStatement, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ArticleUtils.cutTime(articleList);
	}

	/**
	 * 获取所有文章
	 * @return 有序的文章对象列表
	 */
	@Override
	public List<Article> getArticles() {
		List<Article> articleList = new ArrayList<>();
		String sql = "select * from article";
		try {
			ResultSet resultSet = DBUtils.executeQuery(connection, sql);
			while (resultSet.next()) {
				Article article = getNewArticle(resultSet);
				articleList.add(article);
			}
			DBUtils.close(resultSet);
			Collections.sort(articleList);	// 使用 Article 实现的排序方法对 Article 进行排序
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}

	/**
	 * 获取数据库中最新的文章
	 * @return 最新的文章或者 null
	 */
	@Override
	public Article getLatestArticle() {
		String sql = "select * from article order by time desc limit 1";
		return getOneArticleBySQL(sql);
	}

	/**
	 * 返回当前文章的下一篇文章
	 * @param id 当前文章的 id
	 * @return 文章对象或者 null
	 */
	@Override
	public Article getLastArticle(Integer id) {
		String sql = "select * from article where id < '" + id + "'order by time limit 1";
		return getOneArticleBySQL(sql);
	}

	/**
	 * 返回当前文章的上一篇文章
	 * @param id 当前文章的 id
	 * @return 文章对象或者 null
	 */
	@Override
	public Article getNextArticle(Integer id) {
		String sql = "select * from article where id > '" + id + "'order by time limit 1";
		return getOneArticleBySQL(sql);
	}

	/**
	 * 新增一篇文章
	 * @param article 新的文章对象
	 * @return 最新的文章
	 */
	@Override
	public Article addArticle(Article article) {
		String sql = "insert into article values(null, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, article.getTitle());
			preparedStatement.setString(2, article.getAuthor());
			preparedStatement.setString(3, article.getSort());
			preparedStatement.setString(4, article.getTime());
			preparedStatement.setString(5, article.getContent());
			preparedStatement.executeUpdate();
			DBUtils.close(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.getLatestArticle();
	}

	/**
	 * 凭借 id 删除一篇文章
	 * @param id 文章 id
	 * @return true or false
	 */
	@Override
	public Boolean deleteArticle(Integer id) {
		int result = 0;
		String sql = "delete from article where id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			DBUtils.close(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result != 0;
	}

	/**
	 * 根据 search_key 返回文章数或者分类数
	 * @param search_key 查询的 key，已经被定义为类的静态变量
	 * @return 总数
	 */
	@Override
	public int count(String search_key) {
		int result = 0;
		String sql = null;
		if (search_key.equals(SEARCH_ARTICLE)) {
			sql = "select count(id) from article";
		} else if (search_key.equals(SEARCH_SORT)) {
			sql = "select count(distinct(sort)) from article";
		}
		try {
			ResultSet resultSet = DBUtils.executeQuery(connection, sql);
			if (resultSet.next()) {
				result = resultSet.getInt(1);	// 参数为列索引
			}
			DBUtils.close(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取所有分类
	 * @return 有序的分类列表
	 */
	@Override
	public List getSorts() {
		String sql = "select distinct(sort) from article order by sort";
		List list = new ArrayList();
		try {
			ResultSet resultSet = DBUtils.executeQuery(connection, sql);
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			DBUtils.close(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 更新分类
	 * @param oldSort 原分类
	 * @param newSort 新分类
	 * @return 成功与否
	 */
	@Override
	public Boolean updateSort(String oldSort, String newSort) {
		int result = 0;
		String sql = "update article set sort=? where sort=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newSort);
			preparedStatement.setString(2, oldSort);
			result = preparedStatement.executeUpdate();
			DBUtils.close(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	/**
	 * 删除分类 -- 将文章从原分类移动到“未分类”
	 * @param sort 分类名
	 * @return 成功与否
	 */
	@Override
	public Boolean deleteSort(String sort) {
		int result = 0;
		String sql = "update article set sort=? where sort=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "未分类");
			preparedStatement.setString(2, sort);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}
}
