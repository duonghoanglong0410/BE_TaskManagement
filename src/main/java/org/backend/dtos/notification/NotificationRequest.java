package org.backend.dtos.notification;

import org.backend.enums.NotificationType;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {
    private Long userId;
    private Long taskId;
    private String message;
    private NotificationType notificationType;
    private String targetUrl;
    // ...getter, setter...
}