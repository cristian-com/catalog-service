package com.cristian.posts.domain.valueobjects;

import com.cristian.buildingblocks.domain.ValueObject;
import lombok.NonNull;

import static java.util.Objects.requireNonNull;

public record Tag(@NonNull String value) implements ValueObject {

    public static Tag of(String tag) {
        requireNonNull(tag);

        return new Tag(tag);
    }

}
