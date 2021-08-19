package com.cristian.posts.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
public class Reactions {

    private final Map<ReactionType, Set<Reaction>> entriesByType;

    enum ReactionType {
        LIKE,
        LOVE
    }

}
