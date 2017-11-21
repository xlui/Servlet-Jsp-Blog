package style.dx.java.service;

import style.dx.java.dao.TagDao;
import style.dx.java.daoImpl.TagDaoImplement;
import style.dx.java.model.Article;
import style.dx.java.model.Tag;
import style.dx.java.utils.ArticleUtils;
import style.dx.java.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagService {
	private TagDao tagDao;
	private static TagService instance;

	private TagService() {
		tagDao = TagDaoImplement.getInstance();
	}

	public static TagService getInstance() {
		if (instance == null) {
			instance = new TagService();
		}
		return instance;
	}

	public List<Tag> getTagById(String id) {
		return tagDao.getTagByColumn("id", id);
	}

	public List<Tag> getAllTags() {
		return tagDao.getTags();
	}

	public int getTagCount() {
		return tagDao.getTags().size();
	}

	public Map getTagMappedArticles(String tagName) {
		ArticleService articleService = ArticleService.getInstance();
		Map map = new HashMap();
		List<Tag> tagList;
		List<Article> articleList;

		if (tagName.equals("all") || StringUtils.isEmpty(tagName)) {
			tagList = tagDao.getTags();
		} else {
			tagList = tagDao.getTagByColumn("tag", tagName);
		}

		for (Tag tag : tagList) {
			List<Tag> exactTagList = tagDao.getTagByColumn("tag", tag.getTag());
			articleList = new ArrayList<>();

			for (Tag exactTag : exactTagList) {
				List<Article> matchIdArticles = articleService.getArticle("id", String.valueOf(exactTag.getId()));
				articleList.add(ArticleUtils.cutTime(matchIdArticles.get(0)));
			}

			map.put(tag.getTag(), articleList);
		}
		return map;
	}
}
