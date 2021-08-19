package com.cristian.buildingblocks.infrastructure.web;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Resource {

    default Response created(String id) {
        return Response.created(URI.create(id))
                .build();
    }

    default Response deleted() {
        return Response.noContent()
                .build();
    }

}
