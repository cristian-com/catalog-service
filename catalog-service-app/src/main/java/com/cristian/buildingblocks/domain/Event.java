package com.cristian.buildingblocks.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

@Data
public abstract class Event<B extends Serializable> {

    private final UUID id;
    private final B body;
    private final Instant timestamp;
    private final EventType eventType;

    public Event(UUID id, B body, Instant timestamp, EventType eventType) {
        requireNonNull(body);

        this.id =  requireNonNullElse(id, UUID.randomUUID());
        this.body = body;
        this.timestamp = requireNonNullElse(timestamp, Instant.now());
        this.eventType = eventType;
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

    public enum EventType {
        CREATE, UPDATE, DELETE;
    }

}
