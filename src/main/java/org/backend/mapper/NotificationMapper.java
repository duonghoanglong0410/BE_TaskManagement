package org.backend.mapper;

import org.backend.models.Notification;
import org.backend.dtos.notification.NotificationRequest;
import org.backend.dtos.notification.NotificationResponse;
import org.backend.dtos.notification.NotificationSocketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    NotificationResponse toNotificationResponse(Notification notification);

    Notification toNotification(NotificationRequest request);

    NotificationSocketResponse toNotificationSocketResponse(Notification notification);
}