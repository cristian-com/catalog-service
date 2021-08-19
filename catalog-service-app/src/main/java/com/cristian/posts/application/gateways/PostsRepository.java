package com.cristian.posts.application.gateways;

import com.cristian.posts.domain.entities.Post;

public interface PostsRepository {

    Post save(Post post);

}
