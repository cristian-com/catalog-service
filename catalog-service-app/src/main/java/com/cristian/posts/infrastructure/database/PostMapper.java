package com.cristian.posts.infrastructure.database;

import com.cristian.buildingblocks.domain.Identifier;
import com.cristian.buildingblocks.domain.UuidIdentifier;
import com.cristian.external.database.PostEntity;
import com.cristian.external.database.PrivacyEntity;
import com.cristian.external.database.StatusEntity;
import com.cristian.posts.domain.entities.Post;
import com.cristian.posts.domain.valueobjects.Content;
import com.cristian.posts.domain.valueobjects.Privacy;
import com.cristian.posts.domain.valueobjects.Reaction;
import com.cristian.posts.domain.valueobjects.Reactions;
import com.cristian.posts.domain.valueobjects.Status;
import com.cristian.posts.domain.valueobjects.Tag;
import com.cristian.posts.domain.valueobjects.Tags;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.time.Instant;

import static java.util.Objects.isNull;

@Mapper
public interface PostMapper {

    PostEntity post(Post post);

    @InheritInverseConfiguration
    Post post(PostEntity post);

    default String content(Content content) {
        if (isNull(content)) {
            return null;
        }

        return content.toString();
    }

    default Content content(String content) {
        if (isNull(content)) {
            return null;
        }

        return new Content("Example", content);
    }

    default String tags(Tag tag) {
        if (isNull(tag)) {
            return null;
        }

        return tag.toString();
    }

    default Tags tags(String tags) {
        if (isNull(tags)) {
            return null;
        }

        return Tags.empty();
    }

    default String tags(Tags tags) {
        if (isNull(tags)) {
            return null;
        }

        return tags.toString();
    }

    default Tag tag(String tag) {
        if (isNull(tag)) {
            return null;
        }

        return new Tag("Example");
    }

    default String reaction(Reaction reaction) {
        if (isNull(reaction)) {
            return null;
        }

        return reaction.toString();
    }

    default Reaction reaction(String reaction) {
        if (isNull(reaction)) {
            return null;
        }

        return new Reaction(UuidIdentifier.random(), Instant.now());
    }


    default String reactions(Reactions reactions) {
        if (isNull(reactions)) {
            return null;
        }

        return reactions.toString();
    }

    default Reactions reactions(String reactions) {
        if (isNull(reactions)) {
            return null;
        }

        return Reactions.empty();
    }
    default Long id(Identifier id) {
        if (isNull(id)) {
            return null;
        }

        return 1L;
    }

    default UuidIdentifier id(Long id) {
        if (isNull(id)) {
            return null;
        }

        return UuidIdentifier.random();
    }

    PrivacyEntity privacy(Privacy privacy);

    @InheritInverseConfiguration
    Privacy privacy(PrivacyEntity privacy);

    StatusEntity status(Status status);

    @InheritInverseConfiguration
    Status status(StatusEntity status);

}
