package project.pms.dao;

import com.project.pms.dao.*;
import com.project.pms.model.Project;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;

import java.util.List;

public class TestProjectDAOImpl {

    private ProjectDAO projectDAO;
    boolean ignore;
    private InitiateDatabaseDAO initiateDatabaseDAO;

    @Before
    public void init() {
        initiateDatabaseDAO = new InitiateDatabaseDAO();
        if (initiateDatabaseDAO.databaseContainsTables() !=0 ) {
            projectDAO = new ProjectDAOImpl();
            ignore = false;
        } else {
            ignore = true;
        }
    }

    @Test
    public void testRecordsExistInProjectTable() {
        assumeFalse(ignore);
        List<Project> projects = (List<Project>) projectDAO.getAll();
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }

    @Test
    public void testCreateAndDelete() {
        assumeFalse(ignore);
        Project project = new Project();
        project.setName("Test project");
        project.setShortName("TP");
        project.setDescription("Some description");
        Long id = projectDAO.create(project);
        assertNotNull(id);
        project.setId(id);
        Project insertedProject = projectDAO.getById(id);
        assertNotNull(insertedProject);
        assertTrue(projectDAO.delete(project));
    }
}
