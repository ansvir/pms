package com.project.pms.servlet;

import com.project.pms.dao.*;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;
import com.project.pms.response.TaskResponse;
import com.project.pms.util.JsonProcessor;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/task/all")
public class AllTasksServlet extends HttpServlet {

    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;
    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        String jsonArrayString = JsonProcessor.convertListOfObjectsToJson(taskResponseArray);
        response.getWriter().println(jsonArrayString);
    }
}
