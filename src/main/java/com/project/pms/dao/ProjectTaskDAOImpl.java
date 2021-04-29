package com.project.pms.dao;

import com.project.pms.connector.DBConnector;
import com.project.pms.model.ProjectTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectTaskDAOImpl implements DAO<ProjectTask>{

    private final static String SQL_GET_ALL_PROJECTS_TASKS = "SELECT * FROM project_task";
    private final static String SQL_GET_PROJECT_TASKS_BY_PROJECT_ID = "SELECT * FROM project_task WHERE project_id = ?";
    private final static String SQL_GET_PROJECTS_TASK_BY_TASK_ID = "SELECT * FROM project_task WHERE task_id = ?";
    private final static String SQL_INSERT_PROJECT_TASK = "INSERT INTO project_task VALUES (?, ?)";
    private final static String SQL_DELETE_PROJECT_TASK_BY_IDS = "DELETE FROM project_task WHERE project_id = ? AND task_id = ?";

    private final DBConnector CONNECTOR;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public ProjectTaskDAOImpl() {
        CONNECTOR = DBConnector.getInstance();
    }

    @Override
    public Collection<ProjectTask> getAll() {
        List<ProjectTask> projectsTasks = new ArrayList<>();
        try {
            connection = CONNECTOR.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_PROJECTS_TASKS);
            while (resultSet.next()) {
                ProjectTask projectTask = new ProjectTask(
                        resultSet.getLong("project_id"),
                        resultSet.getLong("task_id")
                );
                projectsTasks.add(projectTask);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(statement);
            CONNECTOR.closeConnection();
        }
        return projectsTasks;
    }

    @Override
    public ProjectTask getById(Long id) {
        return null;
    }

    public Collection<ProjectTask> getTasksByProjectId(Long id) {
        List<ProjectTask> projectTasks = new ArrayList<>();
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_PROJECT_TASKS_BY_PROJECT_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    ProjectTask projectTask = new ProjectTask(
                            resultSet.getLong("project_id"),
                            resultSet.getLong("task_id")
                    );
                    projectTasks.add(projectTask);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return projectTasks;
    }

    public Collection<ProjectTask> getProjectsByTaskId(Long id) {
        List<ProjectTask> projectTasks = new ArrayList<>();
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_PROJECTS_TASK_BY_TASK_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    ProjectTask projectTask = new ProjectTask(
                            resultSet.getLong("project_id"),
                            resultSet.getLong("task_id")
                    );
                    projectTasks.add(projectTask);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return projectTasks;
    }

    @Override
    public boolean update(ProjectTask projectTask) {
        return false;
    }

    @Override
    public boolean create(ProjectTask projectTask) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_PROJECT_TASK);
            preparedStatement.setLong(1, projectTask.getProjectId());
            preparedStatement.setLong(2, projectTask.getTaskId());
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
    public boolean delete(ProjectTask projectTask) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_PROJECT_TASK_BY_IDS);
            preparedStatement.setLong(1, projectTask.getProjectId());
            preparedStatement.setLong(2, projectTask.getTaskId());
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
