package org.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(length = 2000)
    private String content;

    private String fileUrl; // Đường dẫn file đính kèm (nếu có)

    private boolean isEdited = false;

    private boolean isDeleted = false;

    private boolean isPinned = false;

    private LocalDateTime sentAt;

    private LocalDateTime editedAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "reply_to")
    private Message replyTo;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    @OneToMany(mappedBy = "message")
    private Set<MessageRecipient> recipients;

    @OneToMany(mappedBy = "message")
    private Set<FileAttachment> attachments;

    // ...getter, setter...
}