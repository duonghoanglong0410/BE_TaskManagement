package org.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.backend.enums.NotificationType;


@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String type;

    private String targetUrl;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

    private LocalDateTime deliveredAt;

    private boolean isRead = false;

    private boolean delivered = false;

    private LocalDateTime deletedAt;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

     @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    // ...getter, setter...
}