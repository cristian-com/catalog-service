package com.cristian.queries;

public interface IItemsCatalogQueryHandler extends QueryHandler<IItemsCatalogQueryHandler.ItemCatalogQuery,
        ItemsCatalog> {

    record ItemCatalogQuery(String name, String category, String tag) implements Query {
    }

}
