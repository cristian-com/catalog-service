package com.cristian.queries;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemsCatalogQueryHandler implements IItemsCatalogQueryHandler {

    final QueryGateway<ItemsCatalog> gateway;

    @Override
    public ItemsCatalog handle(ItemCatalogQuery query) {
        var quer = QueryBuilder.builder()
                .filter("name", query.name())
                .filter("category", query.category())
                .filter("tag", query.tag())
                .build();

        return gateway.get(quer);
    }

}
