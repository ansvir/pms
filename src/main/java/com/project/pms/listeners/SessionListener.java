package com.project.pms.listeners;

import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Project;
import com.project.pms.model.Status;
import com.project.pms.model.Task;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;


@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent e) {
        HttpSession session = e.getSession();
        ProjectDAOImpl prDAO = new ProjectDAOImpl();
        TaskDAOImpl tDAO = new TaskDAOImpl();
        List<Project> projects = (List<Project>) prDAO.getAll();
        List<Task> tasks = (List<Task>) tDAO.getAll();
        session.setAttribute("projects", projects);
        session.setAttribute("tasks", tasks);
        session.setAttribute("statuses", Status.values());
    }
}

