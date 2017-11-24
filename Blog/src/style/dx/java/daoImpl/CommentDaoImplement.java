package style.dx.java.daoImpl;

import style.dx.java.dao.CommentDao;
import style.dx.java.model.Comment;
import style.dx.java.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImplement implements CommentDao {
	private Connection connection = null;
	private static CommentDao instance;

	private CommentDaoImplement() {
		connection = DBUtils.getConnection();
	}

	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDaoImplement();
		}
		return instance;
	}

	@Override
	public boolean addComment(Comment comment) {
		int result = 0;
		String sql = "insert into comment values(null, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, comment.getArticleId());
			preparedStatement.setString(2, comment.getNickName());
			preparedStatement.setString(3, comment.getContent());
			preparedStatement.setString(4, comment.getTime());
			result = preparedStatement.executeUpdate();
			sql = "update article set comment=comment+1 where id=?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
			preparedStatement1.setInt(1, comment.getArticleId());
			preparedStatement1.execute();

			DBUtils.close(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public List<Comment> getComment(int articleId) {
		String sql = "select * from comment where article_id=? order by time";
		List<Comment> commentList = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, articleId);
			ResultSet resultSet = preparedStatement.executeQuery();
			Comment comment = null;
			commentList = new ArrayList<>();
			while (resultSet.next()) {
				comment = new Comment();
				comment.setId(resultSet.getInt("id"));
				comment.setArticleId(resultSet.getInt("article_id"));
				comment.setNickName(resultSet.getString("nickname"));
				comment.setContent(resultSet.getString("content"));
				comment.setTime(resultSet.getString("time"));
				commentList.add(comment);
			}
			DBUtils.close(resultSet, preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentList;
	}
}
