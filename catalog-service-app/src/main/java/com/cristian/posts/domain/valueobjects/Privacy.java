package com.cristian.posts.domain.valueobjects;

import com.cristian.buildingblocks.domain.ValueObject;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public record Privacy(PrivacyValue value) implements ValueObject {
    public static Privacy of(@NotNull String string) {
        requireNonNull(string);

        return new Privacy(PrivacyValue.valueOf(string));
    }

    public enum PrivacyValue {
        PRIVATE, PUBLIC, SCOPED
    }
}
