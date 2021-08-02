package com.cristian.catalog.queries;

import com.cristian.catalog.domain.ItemsCatalog;

public interface IItemsCatalogQueryHandler extends QueryHandler<IItemsCatalogQueryHandler.ItemCatalogQuery,
        ItemsCatalog> {

    record ItemCatalogQuery(String name, String category, String tag) implements Query {
    }

}
