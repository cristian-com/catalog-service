package com.cristian.services;

import com.cristian.buildingblocks.application.commands.Command;
import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.buildingblocks.domain.Aggregate;

public interface CommandProcessorService {

    <C extends Command, R extends Aggregate> CommandExecutionResponse<R> process(C command);

}
