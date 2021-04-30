package com.project.pms.command;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CreateProjectCommand implements ICommand{

    private ProjectDAOImpl projectDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("projectName");
        String shortName = request.getParameter("projectShortName");
        String description = request.getParameter("projectDescription");
        List<Task> tasks = new ArrayList<>();
        Project project = new Project();
        project.setName(name);
        project.setShortName(shortName);
        project.setDescription(description);
        project.setTasks(tasks);
        projectDAO = new ProjectDAOImpl();
        if (projectDAO.create(project) != null) {
            request.setAttribute("successMessage", "Project created successfully!");
        } else {
            request.setAttribute("warningMessage", "Project wasn't created according to some reason");
        }
        return "/jsp/menu.jsp";
    }

}
