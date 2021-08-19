package com.cristian.posts.domain.valueobjects;

import lombok.NonNull;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public record Tags(@NonNull Set<Tag> list) {

    public static Tags of(Set<String> list) {
        requireNonNull(list);

        return new Tags(list.stream().map(Tag::of)
                .collect(Collectors.toSet()));
    }

    public static Tags empty() {
        return new Tags(Collections.emptySet());
    }
}
