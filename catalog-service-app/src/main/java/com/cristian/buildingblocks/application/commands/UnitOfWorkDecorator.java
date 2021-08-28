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
public class UnitOfWorkDecorator<T extends Command,
        R extends Aggregate<? extends Identifier>> implements CommandHandler<T, R> {

    public final CommandHandler<T, R> delegate;
    private final UnitOfWork unitOfWork;

    public CommandExecutionResponse<R> handle(T command) {
        try {
            if (!unitOfWork.begin()) {
                return CommandExecutionResponse.failed(
                        Error.application("Unit of work couldn't be initialised.").toList());
            }

            CommandExecutionResponse<R> result = delegate.handle(command);

            unitOfWork.add(getUnitsOfWork(result.getResponse()));

            if (unitOfWork.complete()) {
                result.getResponse().flush();
            }

            return result;
        } catch (Exception exception) {
            exception.printStackTrace();
            unitOfWork.undo();
            return CommandExecutionResponse.failed(Error.of(exception).toList());
        }
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
            case CREATED -> Operation.create(event.getBody().toString(), event.getTimestamp());
            case UPDATE -> Operation.update(event.getBody().toString(), event.getTimestamp());
            case DELETE -> Operation.delete(event.getBody().toString(), event.getTimestamp());
        };
    }

}
