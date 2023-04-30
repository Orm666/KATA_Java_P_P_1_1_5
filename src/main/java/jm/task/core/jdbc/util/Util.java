package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mybase";
    private static final String USERNAME = "Administrator";
    private static final String PASSWORD = "admin";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ignored) {
        }
        return connection;
    }
}