package com.cristian.catalog.queries;

public interface QueryGateway<T> {

    T get(QueryBuilder queryBuilder);

}
