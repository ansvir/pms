package com.project.pms.servlet;

import com.project.pms.dao.*;
import com.project.pms.model.Project;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;
import com.project.pms.response.TaskResponse;
import com.project.pms.util.JsonProcessor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/task/id")
public class TaskByIdServlet extends HttpServlet {

    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;
    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskDAO.getById(taskId);
        Project project  = projectDAO.getByTaskId(task.getId());
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setName(task.getName());
        taskResponse.setTime(task.getTime());
        taskResponse.setStart(task.getStart().toString());
        taskResponse.setEnd(task.getEnd().toString());
        taskResponse.setStatus(task.getStatus().getId());
        taskResponse.setProjectId(project.getId());
        String jsonArrayString = JsonProcessor.convertObjectToJson(taskResponse);
        response.getWriter().println(jsonArrayString);
    }
}
