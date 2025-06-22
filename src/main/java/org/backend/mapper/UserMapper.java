package org.backend.mapper;

import org.backend.models.User;
import org.backend.dtos.user.UserResponse;
import org.backend.dtos.user.UserShortResponse;
import org.backend.dtos.user.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    UserShortResponse toUserShortResponse(User user);

    User toUser(UserRequest request);
}