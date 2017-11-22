package style.dx.java.utils;

import style.dx.java.dao.ArticleDao;
import style.dx.java.service.ArticleService;
import style.dx.java.service.TagService;

import javax.servlet.http.HttpServletRequest;

public class SideUtils {
	public static void setAttribute(HttpServletRequest request) {
		ArticleService articleService = ArticleService.getInstance();
		TagService tagService = TagService.getInstance();
		request.setAttribute("article_number", articleService.getCount(ArticleDao.SEARCH_ARTICLE));
		request.setAttribute("sort_number", articleService.getCount(ArticleDao.SEARCH_SORT));
		request.setAttribute("tag_number", tagService.getTagCount());
	}
}
