package org.backend.models;

import jakarta.persistence.*;
import org.backend.enums.GroupRole;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_members")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Enumerated(EnumType.STRING)
    private GroupRole role;

    private LocalDateTime joinedAt;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "invited_by")
    private User invitedBy;

    private String note;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    // ...getter, setter...
}