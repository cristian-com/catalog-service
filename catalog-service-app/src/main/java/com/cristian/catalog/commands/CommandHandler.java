package com.cristian.catalog.commands;

public interface CommandHandler<T extends Command> {

    void handle(T command);

}
