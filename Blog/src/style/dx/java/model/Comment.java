package style.dx.java.model;

import java.io.Serializable;

public class Comment implements Serializable {
	private Integer id;
	private String content;
	private String time;
	private Integer userId;
	private Integer articleId;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", content=" + content + ", time=" + time;
	}

	public Comment() {
		super();
	}

    public Comment(Integer id, String content, String time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }

    public Comment(Integer id, String content, String time, Integer userId, Integer articleId) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.userId = userId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
