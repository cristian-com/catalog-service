package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.buildingblocks.domain.Identifier;

public interface CommandHandler<T extends Command, R extends Aggregate<? extends Identifier>> {

    CommandExecutionResponse<R> handle(T command);

}
