package jm.task.core.jdbc.util;

import org.hibernate.cfg.Environment;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "newproject";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getMyConnection() {
       try{ Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME;

        return DriverManager.getConnection(connectionURL, USER_NAME,
                PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
           System.out.println(e.getMessage());
       }
        return null;
    }
}
