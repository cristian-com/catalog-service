package com.cristian.posts.infrastructure.web.v1;

import com.cristian.buildingblocks.infrastructure.web.Resource;
import com.cristian.posts.application.interactors.CreatePostHandler;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("v1/posts")
@RequiredArgsConstructor
public class PostResource implements Resource {

    private final WebRequestMapper requestMapper;
    private final CreatePostHandler postHandler;

    @POST
    public Response post(WebRequestMapper.Post request) {
        var result = postHandler.handle(requestMapper.map(request));
        return created(result.getResponse().getId().toString());
    }

}
