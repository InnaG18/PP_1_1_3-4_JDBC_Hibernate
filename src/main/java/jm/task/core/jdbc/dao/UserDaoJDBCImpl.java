package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            stat.executeUpdate("CREATE TABLE IF NOT EXISTS users(" +
                    "ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), " +
                    "LASTNAME VARCHAR(100), AGE TINYINT, PRIMARY KEY (ID) )");
            System.out.println("Table was created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            stat.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Table was dropped");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMyConnection();
             PreparedStatement preStat = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES(?, ?, ?)")) {

            preStat.setString(1, name);
            preStat.setString(2, lastName);
            preStat.setByte(3, age);

            preStat.executeUpdate();
            System.out.println("User was added!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMyConnection();
             PreparedStatement preStat = connection.prepareStatement("DELETE FROM users WHERE id=?")) {

            preStat.setLong(1, id);

            preStat.executeUpdate();
            System.out.println("User was removed!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            ResultSet resultSet = stat.executeQuery("SELECT id, name, lastName, age FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
            System.out.println("List of users is ready!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMyConnection();
             Statement stat = connection.createStatement()) {

            stat.executeUpdate("DELETE FROM users");
            System.out.println("Table was cleaned!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
