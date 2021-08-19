package com.cristian.queries;

import com.cristian.posts.domain.entities.Post;
import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.Set;

@Value
@Builder
public class ItemsCatalog {

    Set<Post> posts;
    Map<String, String> filters;
    long total;
    long page;

}
