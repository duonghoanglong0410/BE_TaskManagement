package org.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.backend.enums.AuthProvider;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String avatarUrl;

    private String phoneNumber;

    private boolean enabled = true;

    private boolean isLocked = false;

    private String resetToken;

    private String providerId;

    private LocalDateTime registeredAt;

    private LocalDateTime lastLoginAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    private boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider = AuthProvider.LOCAL;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @ManyToOne
    @JoinColumn(name = "deleted_by")
    private User deletedBy;

    @OneToMany(mappedBy = "user")
    private Set<GroupMember> groupMembers;

    @OneToMany(mappedBy = "creator")
    private Set<Task> createdTasks;

    // ...getter, setter...
}