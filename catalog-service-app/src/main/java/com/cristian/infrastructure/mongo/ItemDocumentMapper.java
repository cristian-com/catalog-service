package com.cristian.infrastructure.mongo;

import com.cristian.posts.domain.entities.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ItemDocumentMapper extends DocumentMapper<Post, ItemEntity> {
}
