package com.cristian.posts.infrastructure.database;

import com.cristian.external.database.PostEntity;
import com.cristian.posts.domain.entities.Post;
import com.cristian.posts.application.gateways.PostsRepository;
import lombok.AllArgsConstructor;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
public class PostsRepositoryImpl implements PostsRepository {

    private final PostDocumentMapper mapper;

    @Override
    public Post save(Post post) {
        PostEntity document = mapper.domainEntity(post);
        document.persist();
        return mapper.entity(document);
    }

}
