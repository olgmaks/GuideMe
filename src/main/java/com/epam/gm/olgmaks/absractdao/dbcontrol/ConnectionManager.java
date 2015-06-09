package com.epam.gm.olgmaks.absractdao.dbcontrol;


import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/carhelper";
    public static final String USER_NAME = "root";
    public static final String USER_PASSWORD = "";

    private static Connection connection;

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                new Driver();
                connection = DriverManager.getConnection(DB_URL, USER_NAME,
                        USER_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
