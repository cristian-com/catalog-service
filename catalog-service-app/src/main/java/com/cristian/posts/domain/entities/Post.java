package com.cristian.posts.domain.entities;

import com.cristian.buildingblocks.application.configuration.Default;
import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.buildingblocks.domain.UuidIdentifier;
import com.cristian.posts.domain.events.PostCreated;
import com.cristian.posts.domain.valueobjects.Content;
import com.cristian.posts.domain.valueobjects.Privacy;
import com.cristian.posts.domain.valueobjects.Reactions;
import com.cristian.posts.domain.valueobjects.Status;
import com.cristian.posts.domain.valueobjects.Tags;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

import static java.util.Objects.requireNonNull;

@Data
public class Post extends Aggregate {

    private Status status = Status.active();
    private Privacy privacy;
    private Content content;
    private Reactions reactions;
    private Tags tags;

    @Builder(builderMethodName = "create")
    public Post(UuidIdentifier id, Privacy privacy, Content content, Tags tags) {
        this(id, null, null, Status.active(), privacy, content, tags);

        pushEvent(PostCreated.of(this, "Example"));
    }

    @Default
    @Builder
    public Post(UuidIdentifier id, Instant created, Instant version, Status status,
                Privacy privacy, Content content, Tags tags) {
        super(id, created, version);

        requireNonNull(content);
        requireNonNull(privacy);
        setStatus(status);
        setPrivacy(privacy);
        setContent(content);
        setTags(tags);
    }

    public void setStatus(Status status) {
        requireNonNull(status);
        this.status = status;
    }

}
