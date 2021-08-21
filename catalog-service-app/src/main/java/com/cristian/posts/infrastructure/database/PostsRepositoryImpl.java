package com.cristian.posts.infrastructure.database;

import com.cristian.external.database.PostEntity;
import com.cristian.posts.domain.entities.Post;
import com.cristian.posts.application.gateways.PostsRepository;
import lombok.AllArgsConstructor;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
public class PostsRepositoryImpl implements PostsRepository {

    private final PostMapper mapper;

    @Override
    public Post save(Post post) {
        PostEntity entity = mapper.post(post);
        entity.persist();
        return mapper.post(entity);
    }

}
