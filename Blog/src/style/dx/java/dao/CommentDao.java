package style.dx.java.dao;

import style.dx.java.model.Comment;

import java.util.List;

public interface CommentDao {
	/**
	 * 添加一个 Comment
	 * @param comment comment 对象
	 * @return true or false
	 */
	boolean addComment(Comment comment);

	/**
	 * 获取某个文章的评论
	 * @param articleId 文章 id
	 * @return 评论对象列表
	 */
	List<Comment> getComment(int articleId);
}
