package com.cristian.posts.web.v1;

import com.cristian.buildingblocks.infrastructure.web.Resource;
import com.cristian.posts.commands.CreatePostHandler;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("v1/posts")
@RequiredArgsConstructor
public class PostResource implements Resource {

    private final CreatePostHandler handler;
    private final WebRequestMapper requestMapper;

    @POST
    public Response post(WebRequestMapper.Post request) {
        var response = handler.handle(requestMapper.map(request));
        return created(response);
    }

}
