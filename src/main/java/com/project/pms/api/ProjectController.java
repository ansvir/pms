package com.project.pms.api;

import com.project.pms.dao.ProjectDAO;
import com.project.pms.model.Project;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;

import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/project")
public class ProjectController {

    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> getAllProjects() {
        return (List<Project>) projectDAO.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Project getProjectById(@PathParam("id") Long id) {
        return projectDAO.getById(id);
    }

}
