package com.cristian.posts.infrastructure.web.v1;

import com.cristian.posts.application.commands.CreatePostCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface WebRequestMapper {

    CreatePostCommand map(Post request);

    String map(Put request);

    record Post(String privacy, String content, Set<String> tags, String user) {
    }

    record Put(String privacy, String content, Set<String> tags, String user) {
    }

}
