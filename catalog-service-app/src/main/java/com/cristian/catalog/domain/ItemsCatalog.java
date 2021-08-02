package com.cristian.catalog.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.Set;

@Value
@Builder
public class ItemsCatalog {

    Set<Item> items;
    Map<String, String> filters;
    long total;
    long page;

}
