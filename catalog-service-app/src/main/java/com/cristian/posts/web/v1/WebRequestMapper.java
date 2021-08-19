package com.cristian.posts.web.v1;

import com.cristian.posts.commands.CreatePostCommand;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
interface WebRequestMapper {

    CreatePostCommand map(Post request);

    String map(Put request);

    record Post(String privacy, String content, Set<String> tags, String user) {
    }

    record Put(String privacy, String content, Set<String> tags, String user) {
    }

}
