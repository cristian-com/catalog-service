package com.cristian.posts.gateways;

import com.cristian.posts.domain.entities.Post;

public interface PostRepository {

    Post save(Post post);

}
