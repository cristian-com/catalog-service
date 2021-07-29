package com.cristian.catalog.queries;

import lombok.Builder;
import lombok.Singular;

import java.util.Map;

@Builder
class QueryBuilder {
    @Singular
    private final Map<String, String> filters;
}
    