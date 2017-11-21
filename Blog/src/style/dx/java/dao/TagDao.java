package style.dx.java.dao;

import style.dx.java.model.Tag;

import java.util.List;

public interface TagDao {
	/**
	 * 通过行和值得到 tag
	 * @param column 行
	 * @param value 值
	 * @return Tag 对象列表
	 */
	List<Tag> getTagByColumn(String column, String value);

	/**
	 * 获取所有 Tag
	 * @return Tag 对象列表
	 */
	List<Tag> getTags();

	/**
	 * 新增一个 Tag
	 * @param tag 要新增的 tag 对象
	 * @return true or false
	 */
	boolean addTag(Tag tag);

	/**
	 * 删除一个 Tag
	 * @param tag 要删除的 tag 对象
	 * @return true or false
	 */
	boolean deleteTag(Tag tag);

	/**
	 * 更新一个 Tag
	 * @param oldTag 旧 Tag 字符串
	 * @param newTag 新 Tag 字符串
	 * @return true or false
	 */
	boolean updateTag(String oldTag, String newTag);
}
