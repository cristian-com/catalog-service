package com.cristian.infrastructure.mongo;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;
import org.mapstruct.InheritInverseConfiguration;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public interface DocumentMapper<Entity, Document extends PanacheMongoEntity> {

    default String stringId(ObjectId id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return id.toString();
    }

    default ObjectId objectId(String id) {
        if (Objects.isNull(id)) {
            return null;
        }

        return new ObjectId(id);
    }

    Document document(Entity from);

    @SuppressWarnings("unchecked")
    default Stream<Document> documents(Entity... from) {
        if (isNull(from)) {
            return null;
        }

        return documents(Arrays.asList(from));
    }

    default Stream<Document> documents(Collection<Entity> from) {
        return from.stream().map(this::document);
    }

    @InheritInverseConfiguration
    Entity entity(Document from);

    @SuppressWarnings("unchecked")
    default Stream<Entity> entities(Document... from) {
        return entities(Arrays.asList(from));
    }

    default Stream<Entity> entities(Collection<Document> from) {
        return from.stream().map(this::entity);
    }

}
