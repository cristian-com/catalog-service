package com.cristian.buildingblocks.domain;

import io.vertx.core.impl.ConcurrentHashSet;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.isNull;

public abstract class Aggregate extends Entity {

    protected final Map<Entity, Set<Event<?>>> domainEvents;

    private Aggregate() {
        throw new IllegalStateException("Illegal instantiation.");
    }

    protected Aggregate(Identifier id, Instant created, Instant version) {
        super(id, created, version);
        this.domainEvents = new ConcurrentHashMap<>();
    }

    public Map<Entity, Set<Event<?>>> getDomainEvents() {
        return Collections.unmodifiableMap(domainEvents);
    }

    public void flush() {
        domainEvents.clear();
    }

    protected void pushEvent(Event<?> event) {
        pushEvent(this, event);
    }

    protected void pushEvent(Entity entity, Event<?> event) {
        var newCollection = new ConcurrentHashSet<Event<?>>(3);
        var entityEvents = domainEvents.putIfAbsent(entity, newCollection);

        if (isNull(entityEvents)) {
            newCollection.add(event);
        } else {
            entityEvents.add(event);
        }
    }

}
