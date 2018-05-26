package style.dx.java.model;

import java.io.Serializable;

public class Article implements Serializable {
	private Integer id;
	private String title;
	private String author;
	private String sort;
	private String time;
	private String content;

	public Article() {
		super();
	}

	public Article(Integer id, String title, String author, String sort, String time, String content) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.sort = sort;
		this.time = time;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", author=" + author + ", sort=" + sort + ", time=" + time + ", content=" + content + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
