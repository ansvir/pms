package project.pms.dao;

import com.project.pms.dao.*;
import com.project.pms.model.Project;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

public class TestProjectTaskDAOImpl {

    private ProjectTaskDAO projectTaskDAO;
    private ProjectDAO projectDAO;
    private TaskDAO taskDAO;
    private InitiateDatabaseDAO initiateDatabaseDAO;
    boolean ignore;

    @Before
    public void init() {
        initiateDatabaseDAO = new InitiateDatabaseDAO();
        if (initiateDatabaseDAO.databaseContainsTables() != 0) {
            projectDAO = new ProjectDAOImpl();
            projectTaskDAO = new ProjectTaskDAOImpl();
            taskDAO = new TaskDAOImpl();
            ignore = false;
        } else {
            ignore = true;
        }

    }

    @Test
    public void testCreateAndDelete() {
        assumeFalse(ignore);
        Project project = new Project();
        project.setName("Test project");
        project.setShortName("TP");
        project.setDescription("Some description");
        Task task = new Task();
        task.setName("Test task");
        task.setTime(120);
        task.setStart(new Date(1111000000));
        task.setEnd(new Date(1121000000));
        task.setStatus(Status.ACCOMPLISHED);
        Long projectId = projectDAO.create(project);
        assertNotNull(projectId);
        project.setId(projectId);
        Long taskId = taskDAO.create(task);
        assertNotNull(taskId);
        task.setId(taskId);
        ProjectTask projectTask = new ProjectTask(projectId, taskId);
        Long projectTaskId = projectTaskDAO.create(projectTask);
        assertNotNull(projectTaskId);
        assertTrue(projectTaskDAO.delete(projectTask));
        assertTrue(projectDAO.delete(project));
        assertTrue(taskDAO.delete(task));
    }
}
