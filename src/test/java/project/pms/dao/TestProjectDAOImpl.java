package project.pms.dao;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.model.Project;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class TestProjectDAOImpl {
    @Test
    public void testRecordsExistInProjectTable() {
        ProjectDAOImpl dao = new ProjectDAOImpl();
        List<Project> projects = (List<Project>) dao.getAll();
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
    }
}
