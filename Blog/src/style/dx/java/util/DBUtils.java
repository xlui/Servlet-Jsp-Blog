package style.dx.java.util;

import java.sql.*;

public class DBUtils {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/java_blog";
	private static final String username = "admin";
	private static final String password = "admin";

	public static Connection getInstance() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return Inner.connection;
	}

	private static final class Inner {
		private static Connection connection;

		static {
			try {
				connection = DriverManager.getConnection(DB_URL, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行某个 SQL 语句并返回 ResultSet 对象
	 *
	 * @param connection 数据库连接
	 * @param sql        查询语句
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
		if (resultSet != null) {
			resultSet.close();
		}
	}

	public static void close(Statement statement) throws SQLException {
		if (statement != null) {
			statement.close();
		}
	}

	public static void close(Statement statement, ResultSet resultSet) throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}

	public static void close(ResultSet resultSet, Statement statement) throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}
}
