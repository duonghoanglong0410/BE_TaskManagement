package org.backend.mapper;

import org.backend.models.TaskTag;
import org.backend.dtos.tag.TaskTagRequest;
import org.backend.dtos.tag.TaskTagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskTagMapper {
    TaskTagMapper INSTANCE = Mappers.getMapper(TaskTagMapper.class);

    TaskTagResponse toTaskTagResponse(TaskTag tag);

    TaskTag toTaskTag(TaskTagRequest request);
}