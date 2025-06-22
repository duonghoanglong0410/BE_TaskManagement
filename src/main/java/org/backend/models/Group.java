package org.backend.models;
import org.backend.enums.GroupStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String groupCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    private String description;

    private String avatarUrl;

    private boolean isActive = true;

    private Integer maxMembers;

    private Integer memberCount = 0;

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

    @OneToMany(mappedBy = "group")
    private Set<GroupMember> members;

    @OneToMany(mappedBy = "group")
    private Set<Task> tasks;

     @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupStatus status = GroupStatus.ACTIVE;

    // ...getter, setter...
}