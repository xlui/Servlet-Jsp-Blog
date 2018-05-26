package style.dx.java.model;

import java.io.Serializable;

public class Tag implements Serializable {
	private Integer id;
	private String content;
	private Integer articleId;

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

    public Tag(Integer id, String content, Integer articleId) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
