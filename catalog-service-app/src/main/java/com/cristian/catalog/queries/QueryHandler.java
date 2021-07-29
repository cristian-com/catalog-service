package com.cristian.catalog.queries;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
