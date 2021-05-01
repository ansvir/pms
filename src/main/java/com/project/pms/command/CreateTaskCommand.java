package com.project.pms.command;

import com.project.pms.dao.*;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.CreateTaskCommandQualifier;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.sql.Date;

@CreateTaskCommandQualifier
@Transactional
public class CreateTaskCommand implements ICommand{

    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;
    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("taskName");
        Integer time = Integer.parseInt(request.getParameter("taskTime"));
        Date start = Date.valueOf(request.getParameter("taskStart"));
        Date end = Date.valueOf(request.getParameter("taskEnd"));
        Status status = Status.getById(Long.parseLong(request.getParameter("taskStatus").substring(4)));
        Task task = new Task();
        task.setName(name);
        task.setTime(time);
        task.setStart(start);
        task.setEnd(end);
        task.setStatus(status);
        Long taskId = taskDAO.create(task);
        Long projectId = Long.parseLong(request.getParameter("taskProject").substring(4));
        ProjectTask projectTask = new ProjectTask(projectId, taskId);
        projectTaskDAO.create(projectTask);
        return "/jsp/menu.jsp";
    }
}
