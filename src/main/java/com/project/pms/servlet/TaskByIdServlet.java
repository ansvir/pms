package com.project.pms.servlet;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.Task;
import com.project.pms.util.JsonProcessor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/task/id")
public class TaskByIdServlet extends HttpServlet {

    private TaskDAOImpl taskDAO;
    private ProjectDAOImpl projectDAO;

    @Override
    public void init() {
        taskDAO = new TaskDAOImpl();
        projectDAO = new ProjectDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        Task task = taskDAO.getById(taskId);
        Project project  = projectDAO.getByTaskId(task.getId());
        List<Object> jsonArray = new ArrayList<>();
        jsonArray.add(task);
        jsonArray.add(project);
        jsonArray.add(task.getStatus().getId());
        jsonArray.add(task.getStart().toString());
        jsonArray.add(task.getEnd().toString());
        String jsonArrayString = JsonProcessor.convertListOfObjectsToJson(jsonArray);
        response.getWriter().println(jsonArrayString);
    }
}
