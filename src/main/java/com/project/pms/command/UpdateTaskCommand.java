package com.project.pms.command;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.ProjectTaskDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Status;
import com.project.pms.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class UpdateTaskCommand implements ICommand{

    private TaskDAOImpl taskDAO;
    private ProjectTaskDAOImpl projectTaskDAO;
    private ProjectDAOImpl projectDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("taskId"));
        String name = request.getParameter("taskName");
        Integer time = Integer.parseInt(request.getParameter("taskTime"));
        Date start = Date.valueOf(request.getParameter("taskStart"));
        Date end = Date.valueOf(request.getParameter("taskEnd"));
        Status status = Status.valueOf(request.getParameter("taskStatus").replace(" ", "_"));
        Long projectId = Long.parseLong(request.getParameter("taskProject").substring(4));
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setTime(time);
        task.setStart(start);
        task.setEnd(end);
        task.setStatus(status);
        taskDAO = new TaskDAOImpl();
        taskDAO.update(task);
        projectDAO = new ProjectDAOImpl();
        ProjectTask projectTask = new ProjectTask(projectId, id);
        projectTaskDAO.update(projectTask);
        return "/jsp/menu.jsp";
    }
}
