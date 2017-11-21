package style.dx.java.model;

import style.dx.java.utils.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class Article implements Comparable {
	private int id;
	private String title;
	private String author;
	private String sort;
	private String time;
	private String content;

	public Article() {
		super();
	}

	public Article(int id, String title, String author, String sort, String time, String content) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.sort = sort;
		this.time = time;
		this.content = content;
	}

	/**
	 * 比较两篇文章发布ID的大小
	 * @param o 比较的文章对象
	 * @return 1 0 -1
	 */
	@Override
	public int compareTo(Object o) {
		if (o instanceof Article) {
			Article thatArticle = (Article) o;

			Integer thisId = this.id;
			Integer thatId = thatArticle.getId();

			return thisId.compareTo(thatId);
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", sort=" + sort + ", time=" + time + ", content=" + content + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
