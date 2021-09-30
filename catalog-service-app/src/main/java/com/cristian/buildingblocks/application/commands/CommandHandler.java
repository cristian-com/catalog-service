package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.domain.Aggregate;

public interface CommandHandler<T extends Command, R extends Aggregate> {

    CommandExecutionResponse<R> handle(final T command);

    Class<T> getType();

}
