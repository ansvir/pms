package com.project.pms.command;

import com.project.pms.dao.*;
import com.project.pms.model.Project;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.DeleteProjectCommandQualifier;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;

@DeleteProjectCommandQualifier
@Transactional
public class DeleteProjectCommand implements ICommand {

    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;
    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;
    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;

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
