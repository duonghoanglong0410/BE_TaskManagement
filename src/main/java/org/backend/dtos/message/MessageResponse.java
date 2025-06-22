package org.backend.dtos.message;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long id;
    private Long groupId;
    private Long senderId;
    private String content;
    private String fileUrl;
    private boolean isEdited;
    private boolean isDeleted;
    private boolean isPinned;
    private Long replyToId;
    private LocalDateTime sentAt;
    private LocalDateTime editedAt;
    // ...getter, setter...
}
