package com.project.pms.dao;

import com.project.pms.connector.DBConnector;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.sql.*;

@Stateless
@Transactional
public class InitiateDatabaseDAO {

    private final static String SQL_CREATE_TABLES = "CREATE TABLE IF NOT EXISTS project\n" +
            "(\n" +
            "    id          SERIAL PRIMARY KEY,\n" +
            "    name        VARCHAR,\n" +
            "    short       VARCHAR,\n" +
            "    description VARCHAR\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS task\n" +
            "(\n" +
            "    id     SERIAL PRIMARY KEY,\n" +
            "    name   VARCHAR,\n" +
            "    time   INT,\n" +
            "    start  DATE,\n" +
            "    \"end\"  DATE,\n" +
            "    status VARCHAR\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE IF NOT EXISTS project_task\n" +
            "(\n" +
            "    project_id INT REFERENCES project (id),\n" +
            "    task_id    INT REFERENCES task (id)\n" +
            ")";

    private final DBConnector CONNECTOR;
    private Connection connection;
    private Statement statement;

    public InitiateDatabaseDAO() {
        CONNECTOR = DBConnector.getInstance();
    }

    public int createTables() {
        int rowsAffected = 0;
        try {
            connection = CONNECTOR.getConnection();
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(SQL_CREATE_TABLES);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(statement);
            CONNECTOR.closeConnection();
        }
        return rowsAffected;
    }
}
