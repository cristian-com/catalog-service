package com.cristian.posts.domain.events;

import com.cristian.buildingblocks.domain.Event;
import com.cristian.buildingblocks.domain.Identifier;
import com.cristian.posts.domain.entities.Post;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public class PostCreated extends Event<PostCreated.PostCreatedBody> {

    record PostCreatedBody(Identifier id, String user) implements Serializable {}

    private PostCreated(PostCreatedBody body) {
        super(UUID.randomUUID(), body, Instant.now(), EventType.CREATED);
    }

    public static PostCreated of(Post post, String user) {
        return new PostCreated(new PostCreatedBody(post.getId(), user));
    }

    @Override
    protected String getType() {
        return null;
    }

}
