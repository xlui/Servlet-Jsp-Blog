package style.dx.java.util;

import java.sql.*;

public class DBUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java_blog";
    private static final String username = "admin";
    private static final String password = "admin";

    public static Connection getInstance() {
        return Inner.connection;
    }

    private static final class Inner {
        private static Connection connection;

        static {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, username, password);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException("Cannot connect to MariaDB Server!\n" + e.getMessage());
            }
        }
    }

    /**
     * 执行某个 SQL 语句并返回 ResultSet 对象
     *
     * @param sql 查询语句
     * @return ResultSet 对象，结果集
     */
    public static ResultSet executeQuery(String sql) {
        try {
            PreparedStatement preparedStatement = Inner.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭数据库连接
     */

    public static void close(ResultSet resultSet) {
        try {
            AssertUtils.assertNotNull(resultSet);
            resultSet.close();
        } catch (Exception ignored) {
        }
    }

    public static void close(Statement statement) {
        try {
            AssertUtils.assertNotNull(statement);
            statement.close();
        } catch (Exception ignored) {
        }
    }

    public static void close(Statement statement, ResultSet resultSet) {
        try {
            AssertUtils.assertNotNull(statement, resultSet);
            statement.close();
            resultSet.close();
        } catch (Exception ignored) {
        }
    }

    public static void close(ResultSet resultSet, Statement statement) {
        try {
            AssertUtils.assertNotNull(resultSet, statement);
            statement.close();
            resultSet.close();
        } catch (Exception ignored) {
        }
    }
}
