package style.dx.java.utils;

import java.sql.*;

public class DBUtils {

	/**
	 * 执行某个 SQL 语句并返回 ResultSet 对象
	 * @param connection 数据库连接
	 * @param sql 查询语句
	 * @return ResultSet 对象，结果集
	 */
	public static ResultSet executeQuery(Connection connection, String sql) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		preparedStatement.close();
		return resultSet;
	}

	/**
	 * 关闭数据库连接
	 */

	public static void close(ResultSet resultSet) throws SQLException {
		if (resultSet != null)
			resultSet.close();
	}

	public static void close(Statement statement) throws SQLException {
		if (statement != null)
			statement.close();
	}
}
