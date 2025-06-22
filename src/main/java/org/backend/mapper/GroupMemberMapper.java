package org.backend.mapper;

import org.backend.models.GroupMember;
import org.backend.dtos.GroupMember.GroupMemberRequest;
import org.backend.dtos.GroupMember.GroupMemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMemberMapper {
    GroupMemberMapper INSTANCE = Mappers.getMapper(GroupMemberMapper.class);

    GroupMemberResponse toGroupMemberResponse(GroupMember groupMember);

    GroupMember toGroupMember(GroupMemberRequest request);
}