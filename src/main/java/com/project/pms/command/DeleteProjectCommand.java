package com.project.pms.command;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.ProjectTaskDAOImpl;
import com.project.pms.model.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteProjectCommand implements ICommand {

    private ProjectTaskDAOImpl projectTaskDAO;
    private ProjectDAOImpl projectDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] projectsIds = request.getParameterValues("projectsCB");
        projectTaskDAO = new ProjectTaskDAOImpl();
        projectDAO = new ProjectDAOImpl();
        for (String id : projectsIds) {
            Project project = projectDAO.getById(Long.parseLong(id));
            projectTaskDAO.deleteByProjectId(project);
            projectDAO.delete(project);
        }
        return "/jsp/menu.jsp";
    }
}
