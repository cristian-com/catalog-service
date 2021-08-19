package com.cristian.buildingblocks.domain;

import lombok.Data;

import java.time.Instant;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Data
public abstract class Entity<T extends Identifier> {

    protected final T id;
    protected final Instant created;
    protected Instant version;

    protected Entity() {
        throw new IllegalStateException("");
    }

    protected Entity(T id, Instant created, Instant version) {
        this.id = id;

        if (isNull(created)) {
            final var time = Instant.now();

            this.created = time;
            if (isNull(version)) {
                this.version = time;
            }
        } else {
            requireNonNull(version);

            this.created = created;
            this.version = version;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return id.equals(entity.id) && created.equals(entity.created) && version.equals(entity.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

}
