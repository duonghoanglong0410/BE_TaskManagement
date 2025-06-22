package org.backend.mapper;

import org.backend.dtos.group.GroupRequest;
import org.backend.models.Group;
import org.backend.dtos.group.GroupResponse;
import org.backend.dtos.group.GroupShortResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupResponse toGroupResponse(Group group);

    GroupShortResponse toGroupShortResponse(Group group);

    Group toGroup(GroupRequest request);
}