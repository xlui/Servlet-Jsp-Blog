package style.dx.java.utils;

import style.dx.java.model.Article;

import java.util.List;

/**
 * 对文章的显示进行处理
 */
public class ArticleUtils {
	/**
	 * 使时间显示更美观
	 * @param list 文章列表
	 * @return 处理后的文章列表
	 */
	public static List<Article> cutTime(List<Article> list) {
		for (Article article : list) {
			article.setTime(article.getTime().substring(0, 11));
		}
		return list;
	}

	/**
	 * 处理单个文章的时间显示
	 * @param article 原始文章
	 * @return 处理后的文章
	 */
	public static Article cutTime(Article article) {
		article.setTime(article.getTime().substring(0, 11));
		return article;
	}

	/**
	 * 裁剪文章内容，避免首页显示过多文字
	 * @param list 文章列表
	 * @return 处理后的文章列表
	 */
	public static List cutContent(List<Article> list) {
		for (Article article : list) {
			if (article.getContent() != null && article.getContent().length() > 351) {
				article.setContent(article.getContent().substring(0, 349) + "...");
			}
		}
		return list;
	}
}
