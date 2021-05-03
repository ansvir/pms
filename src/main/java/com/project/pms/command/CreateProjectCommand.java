package com.project.pms.command;

import com.project.pms.dao.DAO;
import com.project.pms.dao.ProjectDAO;
import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.CreateProjectCommandQualifier;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@CreateProjectCommandQualifier
@Transactional
public class CreateProjectCommand implements ICommand{

    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("projectName");
        String shortName = request.getParameter("projectShortName");
        String description = request.getParameter("projectDescription");
        Project project = new Project();
        project.setName(name);
        project.setShortName(shortName);
        project.setDescription(description);
        if (projectDAO.create(project) != null) {
            request.setAttribute("successMessage", "Project created successfully!");
        } else {
            request.setAttribute("warningMessage", "Project wasn't created according to some reason");
        }
        return "/jsp/menu.jsp";
    }

}
