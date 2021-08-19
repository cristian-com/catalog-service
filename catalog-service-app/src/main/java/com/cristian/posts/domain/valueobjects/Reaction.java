package com.cristian.posts.domain.valueobjects;

import com.cristian.buildingblocks.domain.Identifier;

import java.time.Instant;

public record Reaction(Identifier user, Instant created) {
}
