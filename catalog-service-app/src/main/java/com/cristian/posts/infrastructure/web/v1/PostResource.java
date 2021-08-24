package com.cristian.posts.infrastructure.web.v1;

import com.cristian.buildingblocks.infrastructure.web.Resource;
import com.cristian.posts.application.interactors.CreatePostHandler;
import lombok.RequiredArgsConstructor;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("v1/posts")
@RequiredArgsConstructor
public class PostResource implements Resource {

    @Inject
    WebRequestMapper requestMapper;
    @Inject
    Instance<CreatePostHandler> postHandler;

    @POST
    public Response post(WebRequestMapper.Post request) {
        var result = postHandler.get().handle(requestMapper.map(request));
        return created(result.getResponse().getId().toString());
    }

}
