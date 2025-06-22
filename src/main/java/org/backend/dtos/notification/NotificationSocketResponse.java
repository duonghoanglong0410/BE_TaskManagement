package org.backend.dtos.notification;

import org.backend.enums.NotificationType;
import java.time.LocalDateTime;

public class NotificationSocketResponse {
    private Long id;
    private Long userId;
    private String message;
    private NotificationType notificationType;
    private String targetUrl;
    private LocalDateTime createdAt;
    // ...getter, setter...
}