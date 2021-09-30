package com.cristian.services;

import com.cristian.buildingblocks.application.commands.Command;
import com.cristian.buildingblocks.application.commands.CommandExecutionResponse;
import com.cristian.buildingblocks.application.commands.CommandHandler;
import com.cristian.buildingblocks.application.commands.UnitOfWorkDecorator;
import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.posts.application.commands.CreatePostCommand;
import com.cristian.posts.application.services.CreatePostHandler;
import com.cristian.posts.domain.entities.Post;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.ProcessBean;
import javax.enterprise.util.TypeLiteral;
import java.lang.reflect.Type;
import java.util.Map;

@ApplicationScoped
@RequiredArgsConstructor
public class CommandProcessorServiceImpl implements CommandProcessorService {

    private final CreatePostHandler createPostHandler;
    private final UnitOfWorkDecorator unitOfWorkDecorator;

    @Override
    public <C extends Command, R extends Aggregate> CommandExecutionResponse<R> process(C command) {
        Class<? extends Command> x = command.getClass();
        x.get

        unitOfWorkDecorator.handle((CreatePostHandler<?, ?>) createPostHandler, command);

        /*TypeLiteral<CommandHandler<C, R>> handlerType = new TypeLiteral<>() {};
        String xx = x.get(command.getClass());
        Instance<CommandHandler<C, R>> commandHandlerInstance = handlerInstances.select(command.getClass());
        UnitOfWorkDecorator<C, R> transactionalUnitOfWork = new UnitOfWorkDecorator<>();
        transactionalUnitOfWork.setDelegate(commandHandlerInstance.get());

        return transactionalUnitOfWork.handle(command);*/
        return null;
    }

}
