package style.dx.java.daoImpl;

import style.dx.java.dao.TagDao;
import style.dx.java.model.Tag;
import style.dx.java.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImplement implements TagDao {
	private Connection connection = null;
	private static TagDaoImplement instance;

	private TagDaoImplement() {
		connection = DBUtils.getConnection();
	}

	public static TagDaoImplement getInstance() {
		if (instance == null) {
			instance = new TagDaoImplement();
		}
		return instance;
	}

	/**
	 * 通过行和值得到 Tag 的具体实现
	 * @param column 行
	 * @param value 值
	 * @return Tag 对象列表
	 */
	@Override
	public List<Tag> getTagByColumn(String column, String value) {
		String sql = "select * from tag where " + column + "=?";
		List<Tag> tagList = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, value);
			ResultSet resultSet = preparedStatement.executeQuery();

			Tag tag;
			while (resultSet.next()) {
				tag = new Tag();
				tag.setId(resultSet.getInt("id"));
				tag.setTag(resultSet.getString("tag"));
				tagList.add(tag);
			}
			DBUtils.close(preparedStatement, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagList;
	}

	@Override
	public List<Tag> getTags() {
		String sql = "select * from tag";
		List<Tag> tagList = new ArrayList<>();
		try {
			ResultSet resultSet = DBUtils.executeQuery(connection, sql);
			Tag tag;
			while (resultSet.next()) {
				tag = new Tag();
				tag.setId(0);
				tag.setTag(resultSet.getString("tag"));
				tagList.add(tag);
			}
			DBUtils.close(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagList;
	}

	private int executeSQLByTag(String sql, Tag tag) {
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, tag.getId());
			preparedStatement.setString(2, tag.getTag());
			result = preparedStatement.executeUpdate();
			DBUtils.close(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean addTag(Tag tag) {
		String sql = "insert into tag values(?,?)";
		return executeSQLByTag(sql, tag) != 0;
	}

	@Override
	public boolean deleteTag(Tag tag) {
		String sql = "delete from tag where id=? or tag=?";
		return executeSQLByTag(sql, tag) != 0;
	}

	@Override
	public boolean updateTag(String oldTag, String newTag) {
		String sql = "update tag set tag=? where tag=?";
		int result = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newTag);
			preparedStatement.setString(2, oldTag);
			result = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result != 0;
	}
}
