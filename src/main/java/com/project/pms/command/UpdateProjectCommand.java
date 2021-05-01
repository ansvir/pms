package com.project.pms.command;

import com.project.pms.dao.ProjectDAO;
import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.qualifiers.UpdateProjectCommandQualifier;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@UpdateProjectCommandQualifier
@Transactional
public class UpdateProjectCommand implements ICommand{

    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("projectId"));
        String name = request.getParameter("projectName");
        String shortName = request.getParameter("projectShortName");
        String description = request.getParameter("projectDescription");
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setShortName(shortName);
        project.setDescription(description);
        if (projectDAO.update(project)) {
            request.setAttribute("successMessage", "Project updated successfully!");
        } else {
            request.setAttribute("warningMessage", "Project wasn't updated according to some reason");
        }
        return "/jsp/menu.jsp";
    }

}
