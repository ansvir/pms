package com.project.pms.dao;

import com.project.pms.connector.DBConnector;
import com.project.pms.model.Status;
import com.project.pms.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskDAOImpl implements DAO<Task>{

    private final static String SQL_GET_ALL_TASKS = "SELECT * FROM task";
    private final static String SQL_GET_TASKS_BY_ID = "SELECT * FROM task WHERE id = ?";
    private final static String SQL_INSERT_TASKS = "INSERT INTO task (name, time, start, end, status) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_DELETE_TASKS_BY_ID = "DELETE FROM task WHERE id = ?";

    private final DBConnector CONNECTOR;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public TaskDAOImpl() {
        CONNECTOR = DBConnector.getInstance();
    }

    @Override
    public Collection<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            connection = CONNECTOR.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_TASKS);
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("time"),
                        resultSet.getDate("start"),
                        resultSet.getDate("end"),
                        Status.valueOf(resultSet.getString("status")));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(statement);
            CONNECTOR.closeConnection();
        }
        return tasks;
    }

    @Override
    public Task getById(Long id) {
        Task task = null;
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_TASKS_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    task = new Task(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("time"),
                            resultSet.getDate("start"),
                            resultSet.getDate("end"),
                            Status.valueOf(resultSet.getString("status"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return task;
    }

    @Override
    public boolean update(Task task) {
        return false;
    }

    @Override
    public boolean create(Task task) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getTime());
            preparedStatement.setDate(3, task.getStart());
            preparedStatement.setDate(4, task.getEnd());
            preparedStatement.setString(5, task.getStatus().toString());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(Task task) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_TASKS_BY_ID);
            preparedStatement.setLong(1, task.getId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return false;
    }
}