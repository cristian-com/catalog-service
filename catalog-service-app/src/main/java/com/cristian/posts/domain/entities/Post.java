package com.cristian.posts.domain.entities;

import com.cristian.buildingblocks.domain.Aggregate;
import com.cristian.buildingblocks.domain.Entity;
import com.cristian.buildingblocks.domain.UuidIdentifier;
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
public class Post extends Aggregate<UuidIdentifier> {

    private Status status = Status.active();
    private Privacy privacy;
    private Content content;
    private Reactions reactions;
    private Tags tags;

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
