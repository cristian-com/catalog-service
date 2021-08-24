package com.cristian.posts.application.interactors;

import com.cristian.buildingblocks.application.commands.CommandHandler;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.domain.entities.Post;

public interface CreatePostHandler extends CommandHandler<CreatePostCommand, Post> {
}
