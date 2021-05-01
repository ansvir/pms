package com.project.pms.servlet;

import com.project.pms.dao.DAO;
import com.project.pms.dao.ProjectDAO;
import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.util.JsonProcessor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/project/id")
public class ProjectByIdServlet extends HttpServlet {

    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        Project project = projectDAO.getById(projectId);
        String jsonProject = JsonProcessor.convertObjectToJson(project);
        response.getWriter().println(jsonProject);
    }
}
