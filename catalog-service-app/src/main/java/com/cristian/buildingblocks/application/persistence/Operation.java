package com.cristian.buildingblocks.application.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.Instant;

import static com.cristian.buildingblocks.application.persistence.OperationType.CREATE;
import static com.cristian.buildingblocks.application.persistence.OperationType.DELETE;
import static com.cristian.buildingblocks.application.persistence.OperationType.UPDATE;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public record Operation(String event, OperationType type, Instant time) {

    public static Operation create(String event, Instant time) {
        return new Operation(event, CREATE, time);
    }

    public static Operation update(String event, Instant time) {
        return new Operation(event, UPDATE, time);
    }

    public static Operation delete(String event, Instant time) {
        return new Operation(event, DELETE, time);
    }

}
