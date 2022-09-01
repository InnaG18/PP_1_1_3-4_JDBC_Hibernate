package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory;
    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "newproject";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getMyConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            String connectionURL = "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME;

            return DriverManager.getConnection(connectionURL, USER_NAME,
                    PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
