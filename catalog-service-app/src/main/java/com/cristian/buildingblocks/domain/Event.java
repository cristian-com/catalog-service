package com.cristian.buildingblocks.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

public abstract class Event<B extends Serializable> {

    private final UUID id;
    private final B body;
    private final Instant timestamp;
    private final OperationType

    public Event(UUID id, B body, Instant timestamp) {
        requireNonNull(body);

        this.id =  requireNonNullElse(id, UUID.randomUUID());
        this.body = body;
        this.timestamp = requireNonNullElse(timestamp, Instant.now());
    }

    protected abstract String getType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event<?> event = (Event<?>) o;
        return id.equals(event.id)
                && getType().equals(event.getType());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    private enum OperationType {
        CREATE, UPDATE, DELETE;
    }

}
