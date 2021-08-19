package com.cristian.buildingblocks.application.persistence;

import com.cristian.buildingblocks.domain.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.Instant;

import static com.cristian.buildingblocks.application.persistence.OperationType.CREATE;
import static com.cristian.buildingblocks.application.persistence.OperationType.DELETE;
import static com.cristian.buildingblocks.application.persistence.OperationType.UPDATE;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public record Operation(Entity<?> entity, OperationType type, Instant time) {

    public static Operation create(Entity<?> entity, Instant time) {
        return new Operation(entity, CREATE, time);
    }

    public static Operation update(Entity<?> entity, Instant time) {
        return new Operation(entity, UPDATE, time);
    }

    public static Operation delete(Entity<?> entity, Instant time) {
        return new Operation(entity, DELETE, time);
    }

}
