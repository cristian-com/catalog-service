package com.cristian.infrastructure.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.List;
import java.util.Set;

public class ItemEntity extends PanacheMongoEntity {

    public String organization;
    public String name;
    public String category;
    public String description;
    public Set<String> tags;
    public List<String> images;

}
