package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20), last_name VARCHAR(20), age TINYINT)";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ignored) {
        }
    }
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ignored) {
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedstatement = Util.getConnection().prepareStatement(sql)) {
            preparedstatement.setString(1 ,name);
            preparedstatement.setString(2, lastName);
            preparedstatement.setInt(3, age);
            preparedstatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException ignored) {
        }
    }
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ? LIMIT 1";
        try (PreparedStatement preparedstatement = Util.getConnection().prepareStatement(sql)) {
            preparedstatement.setLong(1 , id);
            preparedstatement.executeUpdate();
        } catch (SQLException ignored) {
        }

    }
    public List<User> getAllUsers() {
        List<User> userList = new LinkedList<>();
        String sql = "SELECT * FROM users";
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
            }
        } catch (SQLException ignored) {
        }
        return userList;
    }
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ignored) {
        }
    }
}
