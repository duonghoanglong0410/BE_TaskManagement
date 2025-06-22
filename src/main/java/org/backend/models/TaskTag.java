package org.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tags")
public class TaskTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String color;

    private String description;

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

    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks;

    // ...getter, setter...
}