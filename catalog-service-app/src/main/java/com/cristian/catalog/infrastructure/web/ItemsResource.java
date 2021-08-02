package com.cristian.catalog.infrastructure.web;

import com.cristian.catalog.commands.CreateItemCommand;
import com.cristian.catalog.commands.CreateItemHandler;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemsResource {

    @Inject
    CreateItemHandler handler;

    @POST
    public Response hello(CreateItemCommand command) {
        handler.handle(command);

        return Response
                .created(URI.create("/hello/1"))
                .build();
    }

}
