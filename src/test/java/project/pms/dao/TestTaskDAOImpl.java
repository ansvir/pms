package project.pms.dao;

import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestTaskDAOImpl {

    private TaskDAOImpl taskDAO;

    @Before
    public void init() {
        taskDAO = new TaskDAOImpl();
    }

    @Test
    public void testCreateAndDelete() {
        Task task = new Task();
        task.setName("Test task");
        task.setTime(120);
        task.setStart(new Date(1111000000));
        task.setEnd(new Date(1121000000));
        task.setStatus(Status.ACCOMPLISHED);
        Long id = taskDAO.create(task);
        assertNotNull(id);
        task.setId(id);
        assertTrue(taskDAO.delete(task));
    }
}
