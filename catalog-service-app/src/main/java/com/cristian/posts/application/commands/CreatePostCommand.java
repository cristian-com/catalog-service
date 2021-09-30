package com.cristian.posts.application.commands;

import com.cristian.buildingblocks.application.commands.Command;
import com.cristian.buildingblocks.domain.Identifier;
import com.cristian.buildingblocks.domain.Version;

import java.util.Set;

public record CreatePostCommand(String privacy, String content, Set<String> tags, String user)
        implements Command {
    @Override
    public Identifier id() {
        return null;
    }
    @Override
    public Identifier getCommandType() {
        return null;
    }
    @Override
    public Version getVersion() {
        return null;
    }
}
