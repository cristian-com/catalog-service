package com.cristian.catalog.queries;

import lombok.AllArgsConstructor;

import javax.xml.catalog.Catalog;

@AllArgsConstructor
public class CatalogService implements ICatalogService {

    final QueryGateway<Catalog> gateway;

    @Override
    public Catalog handle(ItemCatalogQuery query) {
        var quer = QueryBuilder.builder()
                .filter("name", query.name())
                .filter("category", query.category())
                .filter("tag", query.tag())
                .build();

        return gateway.get(quer);
    }

}
