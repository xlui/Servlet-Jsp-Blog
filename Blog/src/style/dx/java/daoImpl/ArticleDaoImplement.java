package style.dx.java.daoImpl;

import style.dx.java.dao.ArticleDao;
import style.dx.java.model.Article;
import style.dx.java.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDaoImplement implements ArticleDao {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/java_blog";
	private static final String username = "admin";
	private static final String password = "admin";
	private Connection connection = null;
	private static ArticleDao instance;

	private ArticleDaoImplement() throws SQLException {
		this.connection = DriverManager.getConnection(DB_URL, username, password);
	}

	/**
	 * 单例模式，始终只有一个数据库连接实例
	 * @return 操作数据库的实例
	 */
	public static ArticleDao getInstance() {
		if (instance == null) {
			try {
				instance = new ArticleDaoImplement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
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
				resultSet.getString("content"));
	}

	/**
	 * 执行 SQL 语句获取数据库中下一篇文章
	 * @param sql 查询语句
	 * @return 文章对象
	 */
	private Article getOneArticleBySQL(String sql) throws SQLException {
		Article article = null;
		ResultSet resultSet = DBUtils.executeQuery(connection, sql);
		if (resultSet.next()) {
			article = getNewArticle(resultSet);
		}
		DBUtils.close(resultSet);
		return article;
	}

	/**
	 * 获取所有文章
	 * @return 有序的文章对象列表
	 */
	@Override
	public List getAllArticle() {
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
		try {
			return getOneArticleBySQL(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回当前文章的下一篇文章
	 * @param time 当前文章的时间
	 * @return 文章对象或者 null
	 */
	@Override
	public Article getLastArticle(String time) {
		String sql = "select * from article where time < '" + time + "'order by time desc";
		try {
			return getOneArticleBySQL(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 返回当前文章的上一篇文章
	 * @param time 当前文章的时间
	 * @return 文章对象或者 null
	 */
	@Override
	public Article getNextArticle(String time) {
		String sql = "select * from article where time > '" + time + "'order by time";
		try {
			return getOneArticleBySQL(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			PreparedStatement preparedStatement = connection.prepareStatement("sql");
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
	public int getCount(String search_key) {
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
	public List getAllSort() {
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