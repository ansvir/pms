package com.project.pms.connector;

import com.project.pms.manager.DatabaseManager;

import java.sql.*;
import java.util.MissingResourceException;

public class DBConnector {

    private final String URL = DatabaseManager.getProperty("db.url");
    private final String USER = DatabaseManager.getProperty("db.user");
    private final String PASS = DatabaseManager.getProperty("db.password");
    private static DBConnector instance;
    private static Connection connection;

    private DBConnector() {}

    public static synchronized DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
            return instance;
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection != null) {
                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(URL, USER, PASS);
                    return connection;
                }
            } else {
                connection = DriverManager.getConnection(URL, USER, PASS);
            }
            return connection;
        } catch (SQLException | MissingResourceException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Statement getStatement() throws SQLException {
        if (connection != null) {
            return connection.createStatement();
        }
        return null;
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
