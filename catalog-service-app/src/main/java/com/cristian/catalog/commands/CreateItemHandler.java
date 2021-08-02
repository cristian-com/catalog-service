package com.cristian.catalog.commands;

import com.cristian.catalog.domain.Item;
import com.cristian.catalog.domain.ItemGateway;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
@AllArgsConstructor
public class CreateItemHandler implements CommandHandler<CreateItemCommand> {

    private final ItemGateway itemGateway;

    @Override
    public void handle(CreateItemCommand command) {
        var item = Item.builder()
                .id(UUID.randomUUID())
                .name(command.name())
                .description(command.description())
                .category(command.category())
                .organization(UUID.fromString(command.organization()))
                .images(command.images())
                .tags(command.tags())
                .build();

        itemGateway.save(item);
    }

}
