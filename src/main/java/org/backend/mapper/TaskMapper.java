package org.backend.mapper;

import org.backend.models.Task;
import org.backend.dtos.task.TaskResponse;
import org.backend.dtos.task.TaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UserMapper.class, GroupMapper.class})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "creator", target = "creator")
    @Mapping(source = "group", target = "group")
    TaskResponse toTaskResponse(Task task);

    Task toTask(TaskRequest request);
}