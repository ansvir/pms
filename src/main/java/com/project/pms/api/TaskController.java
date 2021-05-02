package com.project.pms.api;

import com.project.pms.dao.ProjectDAO;
import com.project.pms.dao.ProjectTaskDAO;
import com.project.pms.dao.TaskDAO;
import com.project.pms.model.Project;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;
import com.project.pms.response.TaskResponse;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/task")
public class TaskController {

    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;

    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;

    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TaskResponse> getAllTasks() {
        List<Task> tasks = (List<Task>) taskDAO.getAll();
        List<TaskResponse> taskResponseArray = new ArrayList<>();
        tasks.forEach(el -> {
            Long projectId = projectTaskDAO.getProjectByTaskId(el.getId()).getProjectId();
            TaskResponse taskResponse = new TaskResponse(
                    el.getId(),
                    el.getName(),
                    el.getTime(),
                    el.getStart().toString(),
                    el.getEnd().toString(),
                    el.getStatus().getId(),
                    projectId
            );
            taskResponseArray.add(taskResponse);
        });

        return taskResponseArray;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse getTaskById(@PathParam("id") Long id) {
        Task task = taskDAO.getById(id);
        Project project  = projectDAO.getByTaskId(task.getId());
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setTime(task.getTime());
        taskResponse.setStart(task.getStart().toString());
        taskResponse.setEnd(task.getEnd().toString());
        taskResponse.setStatus(task.getStatus().getId());
        taskResponse.setProjectId(project.getId());
        return taskResponse;
    }
}
