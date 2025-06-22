package org.backend.mapper;

import org.backend.models.FileAttachment;
import org.backend.dtos.file.FileAttachmentRequest;
import org.backend.dtos.file.FileAttachmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileAttachmentMapper {
    FileAttachmentMapper INSTANCE = Mappers.getMapper(FileAttachmentMapper.class);

    FileAttachmentResponse toFileAttachmentResponse(FileAttachment file);

    FileAttachment toFileAttachment(FileAttachmentRequest request);
}