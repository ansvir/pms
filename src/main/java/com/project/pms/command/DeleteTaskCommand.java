package com.project.pms.command;

import com.project.pms.dao.ProjectTaskDAO;
import com.project.pms.dao.ProjectTaskDAOImpl;
import com.project.pms.dao.TaskDAO;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.DeleteTaskCommandQualifier;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@DeleteTaskCommandQualifier
@Transactional
public class DeleteTaskCommand implements ICommand {

    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;
    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String[] tasksIds = request.getParameterValues("tasksCB");
        for (String id : tasksIds) {
            Task task = taskDAO.getById(Long.parseLong(id));
            projectTaskDAO.deleteByTaskId(task);
            taskDAO.delete(task);
        }
        return "/jsp/menu.jsp";
    }
}
