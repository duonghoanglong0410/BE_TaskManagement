package org.backend.mapper;

import org.backend.models.Message;
import org.backend.dtos.message.MessageRequest;
import org.backend.dtos.message.MessageResponse;
import org.backend.dtos.message.MessageSocketRequest;
import org.backend.dtos.message.MessageSocketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageResponse toMessageResponse(Message message);

    Message toMessage(MessageRequest request);

    MessageSocketResponse toMessageSocketResponse(Message message);

    Message toMessage(MessageSocketRequest request);
}