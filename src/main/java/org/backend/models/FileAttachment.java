package org.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_attachments")
public class FileAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileUrl;

    private String fileType;

    private Long fileSize;

    private LocalDateTime uploadedAt;

    private LocalDateTime deletedAt;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    // ...getter, setter...
}