package com.cristian.posts.commands;

import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.buildingblocks.application.commands.TransactionalCommandHandler;
import com.cristian.buildingblocks.domain.UuidIdentifier;
import com.cristian.posts.domain.valueobjects.Content;
import com.cristian.posts.domain.valueobjects.Privacy;
import com.cristian.posts.domain.valueobjects.Tags;
import com.cristian.posts.gateways.PostRepository;
import com.cristian.posts.domain.entities.Post;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import static com.cristian.buildingblocks.application.commands.CommandExecutionResponse.success;

@ApplicationScoped
@AllArgsConstructor
public class CreatePostHandler extends TransactionalCommandHandler<CreatePostCommand, Post> {

    private final PostRepository postRepository;

    @Override
    public CommandExecutionResponse<Post> handle(CreatePostCommand command) {
        var post = Post.builder()
                .id(UuidIdentifier.random())
                .privacy(Privacy.of(command.privacy()))
                .content(new Content("the title", command.content()))
                .tags(Tags.of(command.tags()))
                .build();

        return success(postRepository.save(post));
    }

}
