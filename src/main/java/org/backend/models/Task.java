package org.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.backend.enums.TaskPriority;
import org.backend.enums.TaskStatus;
import java.time.LocalDateTime;
import java.util.Set;
import org.backend.enums.TaskType;


@Entity
@Setter
@Getter
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDateTime dueDate;

    private LocalDateTime startDate;

    private LocalDateTime completedAt;

    private LocalDateTime reminderAt;

    private Integer progress = 0;

    private String attachmentUrl;

    private String category;

    private boolean isShared = false;

    private boolean isDeleted = false;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private Long viewCount = 0L;

    private Integer subTaskCount = 0;

    private LocalDateTime lastModifiedAt;

    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    private User lastModifiedBy;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "confirmed_by")
    private User confirmedBy;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @ManyToMany
    @JoinTable(
        name = "task_assignees",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assignees;

    @ManyToMany
    @JoinTable(
        name = "task_tags",
        joinColumns = @JoinColumn(name = "task_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TaskTag> tags;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskType type;

    // ...getter, setter...
}