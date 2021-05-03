package com.project.pms.servlet;

import com.project.pms.command.ActionFactory;
import com.project.pms.command.ICommand;
import com.project.pms.qualifiers.EmptyCommandQualifier;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/action")
public class ActionServlet extends HttpServlet {

    @Inject
    private ActionFactory actionFactory;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String stringCommand = request.getParameter("command");
        ICommand iCommand = actionFactory.defineCommand(stringCommand);
        String page = iCommand.execute(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
