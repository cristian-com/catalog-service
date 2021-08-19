package com.cristian.posts.application.commands;

import com.cristian.buildingblocks.application.commands.Command;

import java.util.Set;

public record CreatePostCommand(String privacy, String content, Set<String> tags, String user)
        implements Command {
}
