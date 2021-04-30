package com.project.pms.command;

import com.project.pms.dao.ProjectTaskDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTaskCommand implements ICommand {

    private ProjectTaskDAOImpl projectTaskDAO;
    private TaskDAOImpl taskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] tasksIds = request.getParameterValues("tasksCB");
        projectTaskDAO = new ProjectTaskDAOImpl();
        taskDAO = new TaskDAOImpl();
        for (String id : tasksIds) {
            Task task = taskDAO.getById(Long.parseLong(id));
            projectTaskDAO.deleteByTaskId(task);
            taskDAO.delete(task);
        }
        return "/jsp/menu.jsp";
    }
}
