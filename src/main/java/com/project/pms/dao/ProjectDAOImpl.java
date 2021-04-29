package com.project.pms.dao;

import com.project.pms.connector.DBConnector;
import com.project.pms.model.Project;
import com.project.pms.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectDAOImpl implements DAO<Project> {

    private final static String SQL_GET_ALL_PROJECTS = "SELECT * FROM project";
    private final static String SQL_GET_PROJECTS_BY_ID = "SELECT * FROM project WHERE id = ?";
    private final static String SQL_INSERT_PROJECT = "INSERT INTO project (name, short, description) VALUES (?, ?, ?)";
    private final static String SQL_DELETE_PROJECT_BY_ID = "DELETE FROM project WHERE id = ?";
    private final static String SQL_GET_PROJECT_BY_TASK_ID = "SELECT * FROM project JOIN project_task pt on project.id = pt.project_id WHERE pt.task_id = ?";

    private final DBConnector CONNECTOR;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public ProjectDAOImpl() {
        CONNECTOR = DBConnector.getInstance();
    }

    @Override
    public Collection<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        try {
            connection = CONNECTOR.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_PROJECTS);
            while (resultSet.next()) {
                List<Task> tasks= new TaskDAOImpl().getByProjectId(resultSet.getLong("id"));
                Project project = new Project(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("short"),
                        resultSet.getString("description"),
                        tasks);
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(statement);
            CONNECTOR.closeConnection();
        }
        return projects;
    }

    @Override
    public Project getById(Long id) {
        Project project = null;
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_PROJECTS_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    List<Task> tasks = new TaskDAOImpl().getByProjectId(resultSet.getLong("id"));
                    project = new Project(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("short"),
                            resultSet.getString("description"),
                            tasks
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return project;
    }

    public Project getByTaskId(Long id) {
        Project project = null;
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_PROJECT_BY_TASK_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    List<Task> tasks = new TaskDAOImpl().getByProjectId(resultSet.getLong("id"));
                    project = new Project(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("short"),
                            resultSet.getString("description"),
                            tasks
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CONNECTOR.closeStatement(preparedStatement);
            CONNECTOR.closeConnection();
        }
        return project;
    }
    @Override
    public boolean update(Project project) {
        return false;
    }

    @Override
    public boolean create(Project project) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_PROJECT);
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getShortName());
            preparedStatement.setString(3, project.getDescription());
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
    public boolean delete(Project project) {
        try {
            connection = CONNECTOR.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE_PROJECT_BY_ID);
            preparedStatement.setLong(1, project.getId());
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
