package com.cristian.catalog.queries;

import javax.xml.catalog.Catalog;

public interface ICatalogService extends QueryHandler<ICatalogService.ItemCatalogQuery, Catalog> {

    record ItemCatalogQuery(String name, String category, String tag) implements Query {
    }

}
