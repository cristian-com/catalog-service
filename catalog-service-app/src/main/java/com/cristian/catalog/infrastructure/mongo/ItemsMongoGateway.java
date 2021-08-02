package com.cristian.catalog.infrastructure.mongo;

import com.cristian.catalog.domain.Item;
import com.cristian.catalog.domain.ItemGateway;

import javax.inject.Singleton;

@Singleton
public class ItemsMongoGateway implements ItemGateway {

    @Override
    public void save(Item item) {
        var document = getEntity(item);
        document.persist();
    }

    private ItemEntity getEntity(Item item) {
        var entity = new ItemEntity();
        entity.name = item.getName();
        entity.description = item.getDescription();
        entity.category = item.getCategory();
        entity.images = item.getImages();
        entity.tags = item.getTags();

        return entity;
    }

}
