package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.application.persistence.Operation;
import com.cristian.buildingblocks.application.persistence.UnitOfWork;
import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.buildingblocks.domain.Entity;
import com.cristian.buildingblocks.domain.Identifier;

import java.util.Map;

public abstract class TransactionalCommandHandler<T extends Command,
        R extends Aggregate<? extends Identifier>> {

    public CommandExecutionResponse<R> process(T command) {

        var unitOfWork = UnitOfWork.instance();

        var result = handle(command);

        unitOfWork.add(result.getResponse().getDomainEvents());

        if (unitOfWork.commit()) {
            result.getResponse().flush();
        }

        return result;
    }

    private Map<Entity<?>, Operation> getUnitsOfWork(R response){
        var domainEvents = response.getDomainEvents();

    }

    protected abstract CommandExecutionResponse<R>  handle(T command);

}
