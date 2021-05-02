package com.project.pms.api;

import com.project.pms.model.Status;
import com.project.pms.response.StatusResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/status")
public class StatusController {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StatusResponse> getAllStatuses() {
        List<StatusResponse> statusResponses = new ArrayList<>();
        for (Status status : Status.values()) {
            StatusResponse statusResponse = new StatusResponse(
                    status.getId(),
                    status,
                    status.getStatus()
            );
            statusResponses.add(statusResponse);
        }
        return statusResponses;
    }
}
