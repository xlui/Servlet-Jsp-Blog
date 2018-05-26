package style.dx.java.dao;

import style.dx.java.model.Article;

import java.util.List;

public interface ArticleDao extends CommonDao<Article, Integer> {
	enum Search {
		ARTICLE("article"), SORT("sort");
		private String key;

		Search(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return this.key;
		}
	}

	Article getLatestArticle();

	Article getLastArticle(Integer id);

	Article getNextArticle(Integer id);

	int count(Search search);

	List<String> getSorts();

	boolean updateSort(String oldSort, String newSort);

	boolean deleteSort(String sort);
}
