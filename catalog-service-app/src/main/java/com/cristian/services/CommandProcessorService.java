package com.cristian.services;

import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.domain.entities.Post;

public interface CommandProcessorService {

    CommandExecutionResponse<Post> process(CreatePostCommand command);

}
