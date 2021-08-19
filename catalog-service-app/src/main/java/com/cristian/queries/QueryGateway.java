package com.cristian.queries;

public interface QueryGateway<T> {

    T get(QueryBuilder queryBuilder);

}
