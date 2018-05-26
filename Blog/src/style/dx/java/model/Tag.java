package style.dx.java.model;

import java.io.Serializable;

public class Tag implements Serializable {
	private Integer id;
	private String content;
	private Integer article_id;

	@Override
	public String toString() {
		return "Tag[id=" + id + ", content=" + content + "]";
	}

	public Tag() {
		super();
	}

	public Tag(Integer id, String content) {
		this.id = id;
		this.content = content;
	}

	public Tag(Integer id, String content, Integer article_id) {
		this.id = id;
		this.content = content;
		this.article_id = article_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
}
