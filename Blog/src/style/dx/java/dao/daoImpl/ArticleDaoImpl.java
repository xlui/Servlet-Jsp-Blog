package style.dx.java.dao.daoImpl;

import style.dx.java.dao.ArticleDao;
import style.dx.java.model.Article;
import style.dx.java.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    private ArticleDaoImpl() {
    }

    public static ArticleDao getInstance() {
        return Inner.articleDao;
    }

    private static final class Inner {
        private static final ArticleDao articleDao = new ArticleDaoImpl();
    }

    /**
     * 从 ResultSet 对象中获取一个 Article 对象
     *
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
     *
     * @param sql 查询语句
     * @return 文章对象
     */
    private Article getOneArticleById(String sql, Integer id) {
        Article article = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = DBUtils.getInstance().prepareStatement(sql);
            if (id != null) {
                preparedStatement.setInt(1, id);
            }
            resultSet = DBUtils.executeQuery(sql);
            if (resultSet.next()) {
                article = getNewArticle(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
        }
        return article;
    }

    @Override
    public List<Article> findAll() {
        ResultSet resultSet = null;
        String sql = "select * from article";
        List<Article> articles = new ArrayList<>();
        try {
            resultSet = DBUtils.executeQuery(sql);
            while (resultSet.next()) {
                Article article = getNewArticle(resultSet);
                articles.add(article);
            }
            articles.sort(Comparator.comparing(Article::getId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
        }
        return articles;
    }

    @Override
    public Article findById(Integer integer) {
        String sql = "select * from article where id=?";
        return getOneArticleById(sql, integer);
    }

    @Override
    public Article insert(Article article) {
        PreparedStatement preparedStatement = null;
        String sql = "insert into article(title, author, sort, time, content) values(?, ?, ?, ?, ?)";
        try {
            preparedStatement = DBUtils.getInstance().prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getAuthor());
            preparedStatement.setString(3, article.getSort());
            preparedStatement.setString(4, article.getTime());
            preparedStatement.setString(5, article.getContent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement);
        }
        return this.getLatestArticle();
    }

    @Override
    public boolean delete(Article article) {
        int result = 0;
        PreparedStatement preparedStatement = null;
        String sql = "delete from article where id=?";
        try {
            preparedStatement = DBUtils.getInstance().prepareStatement(sql);
            preparedStatement.setInt(1, article.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement);
        }
        return result != 0;
    }

    /**
     * 获取数据库中最新的文章
     *
     * @return 最新的文章或者 null
     */
    @Override
    public Article getLatestArticle() {
        String sql = "select * from article order by time desc limit 1";
        return getOneArticleById(sql, null);
    }

    /**
     * 返回当前文章的下一篇文章
     *
     * @param id 当前文章的 id
     * @return 文章对象或者 null
     */
    @Override
    public Article getLastArticle(Integer id) {
        String sql = "select * from article where id < ? order by time limit 1";
        return getOneArticleById(sql, id);
    }

    /**
     * 返回当前文章的上一篇文章
     *
     * @param id 当前文章的 id
     * @return 文章对象或者 null
     */
    @Override
    public Article getNextArticle(Integer id) {
        String sql = "select * from article where id > ? order by time limit 1";
        return getOneArticleById(sql, id);
    }

    @Override
    public int count(Search search) {
        String sql;
        switch (search) {
            case SORT: {
                sql = "select count(*) from article";
                try {
                    return DBUtils.executeQuery(sql).getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case ARTICLE: {
                sql = "select count(distinct sort) from article";
                try {
                    return DBUtils.executeQuery(sql).getInt(1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            default:
                return -1;
        }
    }

    /**
     * 获取所有分类
     *
     * @return 有序的分类列表
     */
    @Override
    public List<String> getSorts() {
        ResultSet resultSet = null;
        String sql = "select distinct sort from article order by sort";
        List<String> sorts = new ArrayList<>();
        try {
            resultSet = DBUtils.executeQuery(sql);
            while (resultSet.next()) {
                sorts.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet);
        }
        return sorts;
    }

    /**
     * 更新分类
     *
     * @param oldSort 原分类
     * @param newSort 新分类
     * @return 成功与否
     */
    @Override
    public boolean updateSort(String oldSort, String newSort) {
        int result = 0;
        PreparedStatement preparedStatement = null;
        String sql = "update article set sort=? where sort=?";
        try {
            preparedStatement = DBUtils.getInstance().prepareStatement(sql);
            preparedStatement.setString(1, newSort);
            preparedStatement.setString(2, oldSort);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement);
        }
        return result > 0;
    }

    /**
     * 删除分类 -- 将文章从原分类移动到“未分类”
     *
     * @param sort 分类名
     * @return 成功与否
     */
    @Override
    public boolean deleteSort(String sort) {
        int result = 0;
        PreparedStatement preparedStatement = null;
        String sql = "update article set sort=? where sort=?";
        try {
            preparedStatement = DBUtils.getInstance().prepareStatement(sql);
            preparedStatement.setString(1, "未分类");
            preparedStatement.setString(2, sort);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(preparedStatement);
        }
        return result > 0;
    }
}
