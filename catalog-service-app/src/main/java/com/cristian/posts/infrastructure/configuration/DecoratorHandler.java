package com.cristian.posts.infrastructure.configuration;

import com.cristian.buildingblocks.application.commands.Command;
import com.cristian.buildingblocks.application.commands.CommandHandler;
import com.cristian.buildingblocks.application.persistence.UnitOfWork;
import com.cristian.buildingblocks.domain.Aggregate;

import javax.enterprise.inject.Instance;

public interface DecoratorHandler<T extends Command, V extends Aggregate> {

    void setDelegate(CommandHandler<T, V> handler);

    void setUnitOfWorkProvider(Instance<UnitOfWork> uowProvider);

}
