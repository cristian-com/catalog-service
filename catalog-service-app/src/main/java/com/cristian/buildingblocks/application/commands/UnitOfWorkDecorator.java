package com.cristian.buildingblocks.application.commands;

import com.cristian.buildingblocks.application.persistence.Operation;
import com.cristian.buildingblocks.application.persistence.UnitOfWork;
import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.buildingblocks.domain.Event;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class UnitOfWorkDecorator {

    Instance<UnitOfWork> unitOfWork;

    public <T extends Command, R extends Aggregate> CommandExecutionResponse<R> handle(
            CommandHandler<T, R> delegate, T command) {
        UnitOfWork uow = unitOfWork.get();
        try {
            if (!uow.begin()) {
                return CommandExecutionResponse.failed(
                        Error.application("Unit of work couldn't be initialised.").toList());
            }

            CommandExecutionResponse<R> result = delegate.handle(command);

            Map<Event<?>, Operation> x = result.getResponse().getDomainEvents().values().stream()
                    .flatMap(Collection::stream)
                    .collect(Collectors.toMap(event -> event, this::getOperation));

            uow.add(x);

            if (uow.complete()) {
                result.getResponse().flush();
            }

            return result;
        } catch (Exception exception) {
            exception.printStackTrace();
            uow.undo();
            return CommandExecutionResponse.failed(Error.of(exception).toList());
        }
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
