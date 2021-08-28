package com.cristian.posts.infrastructure.configuration;

import com.cristian.buildingblocks.application.commands.CommandHandler;
import com.cristian.buildingblocks.application.commands.UnitOfWorkDecorator;
import com.cristian.buildingblocks.application.persistence.UnitOfWork;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.application.gateways.PostsRepository;
import com.cristian.posts.application.interactors.impl.CreatePostHandlerImpl;
import com.cristian.posts.domain.entities.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class CommandHandlersProviders {

    @Produces
    @ApplicationScoped
    public CommandHandler<CreatePostCommand, Post> createPostHandler(PostsRepository postsRepository,
                                                                     UnitOfWork uow) {
        CommandHandler<CreatePostCommand, Post> handler = new CreatePostHandlerImpl(postsRepository);
        return new UnitOfWorkDecorator<>(handler, uow);
    }

}
