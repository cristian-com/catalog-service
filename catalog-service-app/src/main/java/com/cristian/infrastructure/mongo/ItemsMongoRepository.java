package com.cristian.infrastructure.mongo;

import com.cristian.posts.domain.entities.Post;
import com.cristian.posts.gateways.PostRepository;
import lombok.AllArgsConstructor;

import javax.inject.Singleton;

@Singleton
@AllArgsConstructor
public class ItemsMongoRepository implements PostRepository {

    private final ItemDocumentMapper mapper;

    @Override
    public Post save(Post post) {
        var document = mapper.document(post);
        document.persist();

        return post;
    }

}
