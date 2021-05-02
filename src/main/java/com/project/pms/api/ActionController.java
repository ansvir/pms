package com.project.pms.api;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/actiona")
public class ActionController {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String doAction(@NotNull @FormParam("command") String command,
                         @NotNull @FormParam("firstName") String firstName,
                         @NotNull @FormParam("lastName") String lastName,
                         @FormParam("email") String email) {
        return null;
    }
}
