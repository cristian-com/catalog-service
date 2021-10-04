package com.cristian.posts.infrastructure.web.v1;

import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.buildingblocks.infrastructure.web.Resource;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.domain.entities.Post;
import com.cristian.services.CommandProcessorService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("v1/posts")
@RequiredArgsConstructor
public class PostResource implements Resource {

    private final WebRequestMapper requestMapper;
    private final CommandProcessorService commandProcessorService;

    @POST
    public Response post(WebRequestMapper.Post request) {
        CreatePostCommand command = requestMapper.map(request);
        CommandExecutionResponse<Post> result = commandProcessorService.process(command);
        return created(result.getResponse().getId().toString());
    }

}
