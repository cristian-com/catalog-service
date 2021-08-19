package com.cristian.buildingblocks.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class UuidIdentifier implements Identifier {
    UUID id;

    public static UuidIdentifier random() {
        return new UuidIdentifier(UUID.randomUUID());
    }

    public static UuidIdentifier of(String id) {
        return new UuidIdentifier(UUID.fromString(id));
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public int compareTo(Identifier other) {
        return this.equals(other) ? 1 : 0;
    }

}
