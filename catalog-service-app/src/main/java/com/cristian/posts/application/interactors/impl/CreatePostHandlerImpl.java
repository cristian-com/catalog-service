package com.cristian.posts.application.interactors.impl;

import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.buildingblocks.application.commands.CommandHandler;
import com.cristian.buildingblocks.domain.UuidIdentifier;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.application.interactors.CreatePostHandler;
import com.cristian.posts.domain.entities.Post;
import com.cristian.posts.domain.valueobjects.Content;
import com.cristian.posts.domain.valueobjects.Privacy;
import com.cristian.posts.domain.valueobjects.Tags;
import com.cristian.posts.application.gateways.PostsRepository;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import static com.cristian.buildingblocks.application.commands.CommandExecutionResponse.success;

@ApplicationScoped
@AllArgsConstructor
class CreatePostHandlerImpl implements CreatePostHandler {

    private final PostsRepository postsRepository;

    @Override
    public CommandExecutionResponse<Post> handle(final CreatePostCommand command) {
        var post = Post.create()
                .id(UuidIdentifier.random())
                .privacy(Privacy.of(command.privacy()))
                .content(new Content("the title", command.content()))
                .tags(Tags.of(command.tags()))
                .build();

        return success(postsRepository.save(post));
    }

}
