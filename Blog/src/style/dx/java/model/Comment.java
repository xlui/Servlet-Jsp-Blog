package style.dx.java.model;

public class Comment {
	private int id;
	private int articleId;
	private String nickName;
	private String content;
	private String time;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", nickName=" + nickName + ", content=" + content + ", time=" + time;
	}

	public Comment() {
		super();
	}

	public Comment(int id, int articleId, String nickName, String content, String time) {
		this.id = id;
		this.articleId = articleId;
		this.nickName = nickName;
		this.content = content;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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
}
