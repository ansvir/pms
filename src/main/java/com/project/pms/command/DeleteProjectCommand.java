package com.project.pms.command;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.ProjectTaskDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteProjectCommand implements ICommand {

    private ProjectTaskDAOImpl projectTaskDAO;
    private ProjectDAOImpl projectDAO;
    private TaskDAOImpl taskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] projectsIds = request.getParameterValues("projectsCB");
        projectTaskDAO = new ProjectTaskDAOImpl();
        projectDAO = new ProjectDAOImpl();
        taskDAO = new TaskDAOImpl();
        for (String id : projectsIds) {
            Project project = projectDAO.getById(Long.parseLong(id));
            List<ProjectTask> projectTasks = (List<ProjectTask>) projectTaskDAO.getTasksByProjectId(project.getId());
            projectTaskDAO.deleteByProjectId(project);
            projectTasks.forEach(pt -> {
                Task task = new Task();
                task.setId(pt.getTaskId());
                taskDAO.delete(task);
            });
            projectDAO.delete(project);
        }
        return "/jsp/menu.jsp";
    }
}
