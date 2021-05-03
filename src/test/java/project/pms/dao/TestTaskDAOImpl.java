package project.pms.dao;

import com.project.pms.dao.InitiateDatabaseDAO;
import com.project.pms.dao.TaskDAO;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeFalse;

public class TestTaskDAOImpl {

    private TaskDAO taskDAO;
    boolean ignore;
    private InitiateDatabaseDAO initiateDatabaseDAO;

    @Before
    public void init() {
        initiateDatabaseDAO = new InitiateDatabaseDAO();
        if (initiateDatabaseDAO.databaseContainsTables() !=0 ) {
            taskDAO = new TaskDAOImpl();
            ignore = false;
        } else {
            ignore = true;
        }
    }

    @Test
    public void testCreateAndDelete() {
        assumeFalse(ignore);
        Task task = new Task();
        task.setName("Test task");
        task.setTime(120);
        task.setStart(new Date(1111000000));
        task.setEnd(new Date(1121000000));
        task.setStatus(Status.ACCOMPLISHED);
        Long id = taskDAO.create(task);
        assertNotNull(id);
        task.setId(id);
        Task insertedTask = taskDAO.getById(id);
        assertNotNull(insertedTask);
        assertTrue(taskDAO.delete(task));
    }
}
