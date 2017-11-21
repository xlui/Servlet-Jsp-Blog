package style.dx.java.model;

public class Tag {
	private int id;
	private String tag;

	@Override
	public String toString() {
		return "Tag[id=" + id + ", tag=" + tag + "]";
	}

	public Tag() {
		super();
	}

	public Tag(int id, String tag) {
		this.id = id;
		this.tag = tag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
