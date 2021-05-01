package com.project.pms.command;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.ProjectTaskDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Status;
import com.project.pms.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class CreateTaskCommand implements ICommand{

    private ProjectDAOImpl projectDAO;
    private ProjectTaskDAOImpl projectTaskDAO;
    private TaskDAOImpl taskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("taskName");
        Integer time = Integer.parseInt(request.getParameter("taskTime"));
        Date start = Date.valueOf(request.getParameter("taskStart"));
        Date end = Date.valueOf(request.getParameter("taskEnd"));
        Status status = Status.getById(Long.parseLong(request.getParameter("taskStatus").substring(4)));
        Task task = new Task();
        task.setName(name);
        task.setTime(time);
        task.setStart(start);
        task.setEnd(end);
        task.setStatus(status);
        taskDAO = new TaskDAOImpl();
        Long taskId = taskDAO.create(task);
        Long projectId = Long.parseLong(request.getParameter("taskProject").substring(4));
        projectDAO = new ProjectDAOImpl();
        ProjectTask projectTask = new ProjectTask(projectId, taskId);
        projectTaskDAO = new ProjectTaskDAOImpl();
        projectTaskDAO.create(projectTask);
        return "/jsp/menu.jsp";
    }
}
