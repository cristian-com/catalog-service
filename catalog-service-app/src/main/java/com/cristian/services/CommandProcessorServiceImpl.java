package com.cristian.services;

import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.buildingblocks.application.commands.UnitOfWorkDecorator;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.application.services.CreatePostHandler;
import com.cristian.posts.domain.entities.Post;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RequiredArgsConstructor
public class CommandProcessorServiceImpl implements CommandProcessorService {

    private final CreatePostHandler createPostHandler;
    private final UnitOfWorkDecorator unitOfWorkDecorator;

    @Override
    public CommandExecutionResponse<Post> process(CreatePostCommand command) {
        return unitOfWorkDecorator.handle(createPostHandler, command);
    }
}
