package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.domain.Identifier;
import com.cristian.buildingblocks.domain.Version;

import java.io.Serializable;

public interface Command extends Serializable {
    Identifier id();
    Identifier getCommandType();
    Version getVersion();
    Class<? extends Command> getCommandClass();
}
