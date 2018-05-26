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
     *
     * @return 文章列表
     */
    public List getArticles() {
        List<Article> articles = articleDao.findAll();
        ArticleUtils.cutTime(articles);
        ArticleUtils.cutContent(articles);
        return articles;
    }

    public Article getArticle(Integer integer) {
        return articleDao.findById(integer);
    }

    /**
     * 获取上一篇文章
     *
     * @param id 当前文章的 id
     * @return 上一篇文章对象
     */
    public Article getPreviousArticle(Integer id) {
        return articleDao.getLastArticle(id);
    }

    /**
     * 获取下一篇文章
     *
     * @param id 当前文章的 id
     * @return 下一篇文章对象
     */
    public Article getNextArticle(Integer id) {
        return articleDao.getNextArticle(id);
    }

    /**
     * 获取文章或者分类的数量
     *
     * @param search 搜索的键
     * @return 数量
     */
    public int getCount(ArticleDao.Search search) {
        return articleDao.count(search);
    }
}
