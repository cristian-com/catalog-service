package com.cristian.buildingblocks.infrastructure.database;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.mapstruct.InheritInverseConfiguration;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public interface EntityMapper<D, E extends PanacheEntity> {

    E domainEntity(D from);

    @SuppressWarnings("unchecked")
    default Stream<E> domainEntities(D... from) {
        if (isNull(from)) {
            return null;
        }

        return documents(Arrays.asList(from));
    }

    default Stream<E> documents(Collection<D> from) {
        return from.stream().map(this::domainEntity);
    }

    @InheritInverseConfiguration
    D entity(E from);

    @SuppressWarnings("unchecked")
    default Stream<D> entities(E... from) {
        return entities(Arrays.asList(from));
    }

    default Stream<D> entities(Collection<E> from) {
        return from.stream().map(this::entity);
    }

}
