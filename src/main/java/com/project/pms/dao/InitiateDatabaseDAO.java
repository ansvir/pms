package com.project.pms.dao;

import com.project.pms.connector.DBConnector;
import com.project.pms.model.Project;

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
private static final String SQL_GET_TABLES_COUNT = "SELECT count(*) FROM information_schema.tables WHERE table_schema = 'public'";

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

    public long databaseContainsTables() {
        long rows = 0L;
        try {
            connection = CONNECTOR.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_TABLES_COUNT);
            if (resultSet != null) {
                while (resultSet.next()) {
                    rows = Long.parseLong(resultSet.getString(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(statement);
            CONNECTOR.closeConnection();
        }
        return rows;
    }
}
