package style.dx.java;

import java.sql.*;

/**
 * 本项目使用 MariaDB，需要先下载 MariaDB 的驱动导入，https://downloads.mariadb.org/connector-java/2.2.0/
 */
public class DBTest {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/java_blog";
	private static final String username = "admin";
	private static final String password = "admin";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, username, password);
			statement = connection.createStatement();
			String sql = "select * from test";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String author = resultSet.getString("content");

				System.out.println("ID:" + id + ", author=" + author);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
