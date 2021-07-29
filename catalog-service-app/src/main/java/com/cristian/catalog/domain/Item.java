package com.cristian.catalog.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class Item {

    UUID id;
    UUID organization;
    String name;
    String category;
    String description;
    Set<String> tags;
    List<String> images;

}
