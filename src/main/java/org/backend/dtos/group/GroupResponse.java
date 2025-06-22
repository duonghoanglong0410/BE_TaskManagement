package org.backend.dtos.group;

import org.backend.enums.GroupStatus;
import java.time.LocalDateTime;

public class GroupResponse {
    private Long id;
    private String groupCode;
    private String name;
    private String description;
    private String avatarUrl;
    private Integer maxMembers;
    private Integer memberCount;
    private GroupStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getGroupCode() {
        return groupCode;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public Integer getMaxMembers() {
        return maxMembers;
    }
    
    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }
    
    public Integer getMemberCount() {
        return memberCount;
    }
    
    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }
    
    public GroupStatus getStatus() {
        return status;
    }
    
    public void setStatus(GroupStatus status) {
        this.status = status;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
