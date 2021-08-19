package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.application.persistence.Operation;
import com.cristian.buildingblocks.application.persistence.UnitOfWork;
import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.buildingblocks.domain.Event;
import com.cristian.buildingblocks.domain.Identifier;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TransactionalCommandHandler<T extends Command,
        R extends Aggregate<? extends Identifier>> implements CommandHandler<T, R> {

    private final CommandHandler<T, R> handler;

    public CommandExecutionResponse<R> handle(T command) {
        var unitOfWork = UnitOfWork.instance();

        CommandExecutionResponse<R> result = handler.handle(command);

        unitOfWork.add(getUnitsOfWork(result.getResponse()));

        if (unitOfWork.commit()) {
            result.getResponse().flush();
        }

        return result;
    }

    private Map<Event<?>, Operation> getUnitsOfWork(R response) {
        var domainEvents = response.getDomainEvents();
        return domainEvents.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(event -> event, this::getOperation));
    }

    private Operation getOperation(Event<?> event) {
        var eventType = event.getEventType();

        return switch (eventType) {
            case CREATE -> Operation.create(event.getBody().toString(), event.getTimestamp());
            case UPDATE -> Operation.update(event.getBody().toString(), event.getTimestamp());
            case DELETE -> Operation.delete(event.getBody().toString(), event.getTimestamp());
        };
    }

}
