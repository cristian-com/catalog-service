package com.cristian.posts.domain.entities;

import com.cristian.buildingblocks.domain.UuidIdentifier;
import com.cristian.buildingblocks.domain.Entity;
import com.cristian.posts.domain.valueobjects.Content;
import com.cristian.posts.domain.valueobjects.Reactions;
import lombok.Data;

import java.time.Instant;

@Data
public class Comment extends Entity {

    private final Content content;
    private final Reactions reactions;

    public Comment(UuidIdentifier id, Instant created, Instant version,
                   Content content, Reactions reactions) {
        super(id, created, version);
        this.content = content;
        this.reactions = reactions;
    }
}
