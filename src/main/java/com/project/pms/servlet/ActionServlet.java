package com.project.pms.servlet;

import com.project.pms.command.ActionFactory;
import com.project.pms.command.Command;
import com.project.pms.command.ICommand;
import com.project.pms.dao.ProjectDAOImpl;
import com.project.pms.dao.TaskDAOImpl;
import com.project.pms.model.Status;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebServlet("/action")
public class ActionServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(ActionServlet.class.getName());

    private ProjectDAOImpl projectDAO;
    private TaskDAOImpl taskDAO;
    private ActionFactory actionFactory;

    @Override
    public void init() {
        projectDAO = new ProjectDAOImpl();
        taskDAO = new TaskDAOImpl();
        actionFactory = new ActionFactory();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String stringCommand = request.getParameter("command");
        ICommand command = actionFactory.defineCommand(stringCommand);
        String page = command.execute(request, response);
        HttpSession session = request.getSession();
        session.setAttribute("projects", projectDAO.getAll());
        session.setAttribute("tasks", taskDAO.getAll());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
