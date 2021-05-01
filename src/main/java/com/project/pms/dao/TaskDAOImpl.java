package com.project.pms.dao;

import com.project.pms.connector.DBConnector;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.TaskDAOImplQualifier;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
@TaskDAOImplQualifier
@Transactional
public class TaskDAOImpl implements TaskDAO {

    private final static String SQL_GET_ALL_TASKS = "SELECT * FROM task";
    private final static String SQL_GET_TASKS_BY_ID = "SELECT * FROM task WHERE id = ?";
    private final static String SQL_INSERT_TASKS = "INSERT INTO task (name, time, start, \"end\", status) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE_TASK = "UPDATE task SET name = ?, time = ?, start = ?, \"end\" = ?, status = ? WHERE id =?";
    private final static String SQL_INSERT_TASK_INTO_PROJECT_TASK = "INSERT INTO project_task (project_id, task_id) VALUES (?, ?)";
    private final static String SQL_UPDATE_TASK_IN_PROJECT_TASK = "UPDATE project_task SET project_id = ? WHERE task_id = ?";
    private final static String SQL_DELETE_TASKS_BY_ID = "DELETE FROM task WHERE id = ?";
    private final static String SQL_DELETE_TASKS_IDS_IN_PROJECT_TASK_BY_TASK_ID = "DELETE FROM project_task WHERE task_id = ?";
    private final static String SQL_GET_TASKS_BY_PROJECT_ID = "SELECT * FROM task JOIN project_task pt ON task.id = pt.task_id WHERE pt.project_id = ?";

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
                        Status.valueOf(resultSet.getString("status"))
                );
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

    public List<Task> getByProjectId(Long id) {
        List<Task> tasks = new ArrayList<>();
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_TASKS_BY_PROJECT_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("time"),
                        resultSet.getDate("start"),
                        resultSet.getDate("end"),
                        Status.getByStatusName(resultSet.getString("status"))
                );
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
    public boolean update(Task task) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_TASK);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getTime());
            preparedStatement.setDate(3, task.getStart());
            preparedStatement.setDate(4, task.getEnd());
            preparedStatement.setString(5, task.getStatus().toString());
            preparedStatement.setLong(6, task.getId());
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
    public Long create(Task task) {
        Long generatedId = null;
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_TASKS, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getTime());
            preparedStatement.setDate(3, task.getStart());
            preparedStatement.setDate(4, task.getEnd());
            preparedStatement.setString(5, task.getStatus().toString());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                return generatedId;
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getLong(1);
                }
                else {
                    return generatedId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return generatedId;
    }

    @Override
    public boolean delete(Task task) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_TASKS_IDS_IN_PROJECT_TASK_BY_TASK_ID);
            preparedStatement.setLong(1, task.getId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_TASKS_BY_ID);
            preparedStatement.setLong(1, task.getId());
            rowsAffected =
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
