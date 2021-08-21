package com.cristian.posts.infrastructure.database;

import com.cristian.external.database.PostEntity;
import com.cristian.external.database.PrivacyEntity;
import com.cristian.external.database.StatusEntity;
import com.cristian.posts.domain.entities.Post;
import com.cristian.posts.domain.valueobjects.Content;
import com.cristian.posts.domain.valueobjects.Privacy;
import com.cristian.posts.domain.valueobjects.Status;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

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

    PrivacyEntity privacy(Privacy privacy);

    @InheritInverseConfiguration
    Privacy privacy(PrivacyEntity privacy);

    StatusEntity status(Status status);

    @InheritInverseConfiguration
    Status status(StatusEntity status);

}
