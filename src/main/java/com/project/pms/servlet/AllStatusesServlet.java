package com.project.pms.servlet;

import com.project.pms.model.Status;
import com.project.pms.response.StatusResponse;
import com.project.pms.util.JsonProcessor;

import javax.enterprise.context.RequestScoped;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/status/all")
public class AllStatusesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<StatusResponse> statusResponses = new ArrayList<>();
        for (Status status : Status.values()) {
            StatusResponse statusResponse = new StatusResponse(
                    status.getId(),
                    status,
                    status.getStatus()
            );
            statusResponses.add(statusResponse);
        }
        String json = JsonProcessor.convertListOfObjectsToJson(statusResponses);
        response.getWriter().println(json);
    }
}
