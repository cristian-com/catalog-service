package com.cristian.posts.infrastructure.database;

import com.cristian.buildingblocks.infrastructure.database.EntityMapper;
import com.cristian.external.database.PostEntity;
import com.cristian.posts.domain.entities.Post;
import org.mapstruct.Mapper;

@Mapper
public interface PostDocumentMapper extends EntityMapper<Post, PostEntity> {
}
