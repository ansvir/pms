package com.project.pms.command;

import com.project.pms.dao.*;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;
import com.project.pms.qualifiers.UpdateTaskCommandQualifier;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.sql.Date;

@UpdateTaskCommandQualifier
@Transactional
public class UpdateTaskCommand implements ICommand{

    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;
    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("taskId"));
        String name = request.getParameter("taskName");
        Integer time = Integer.parseInt(request.getParameter("taskTime"));
        Date start = Date.valueOf(request.getParameter("taskStart"));
        Date end = Date.valueOf(request.getParameter("taskEnd"));
        Status status = Status.getById(Long.parseLong(request.getParameter("taskStatus").substring(4)));
        Long projectId = Long.parseLong(request.getParameter("taskProject").substring(4));
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setTime(time);
        task.setStart(start);
        task.setEnd(end);
        task.setStatus(status);
        taskDAO.update(task);
        ProjectTask projectTask = new ProjectTask(projectId, id);
        projectTaskDAO.update(projectTask);
        return "/jsp/menu.jsp";
    }
}
