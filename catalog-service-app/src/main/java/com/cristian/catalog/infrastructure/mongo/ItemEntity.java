package com.cristian.catalog.infrastructure.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ItemEntity extends PanacheMongoEntity {

    public UUID organization;
    public String name;
    public String category;
    public String description;
    public Set<String> tags;
    public List<String> images;

}
