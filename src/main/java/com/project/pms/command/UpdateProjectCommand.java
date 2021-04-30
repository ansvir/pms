package com.project.pms.command;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UpdateProjectCommand implements ICommand{

    private ProjectDAOImpl projectDAO;
    private TaskDAOImpl taskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("projectId"));
        String name = request.getParameter("projectName");
        String shortName = request.getParameter("projectShortName");
        String description = request.getParameter("projectDescription");
        taskDAO = new TaskDAOImpl();
        List<Task> tasks = taskDAO.getByProjectId(id);
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setShortName(shortName);
        project.setDescription(description);
        projectDAO = new ProjectDAOImpl();
        if (projectDAO.update(project)) {
            request.setAttribute("successMessage", "Project updated successfully!");
        } else {
            request.setAttribute("warningMessage", "Project wasn't updated according to some reason");
        }
        return "/jsp/menu.jsp";
    }

}
