package org.backend.dtos.task;

import org.backend.enums.TaskPriority;
import org.backend.enums.TaskStatus;
import org.backend.enums.TaskType;
import java.time.LocalDateTime;
import java.util.Set;

public class TaskRequest {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private String category;
    private TaskPriority priority;
    private TaskStatus status;
    private TaskType type;
    private Set<Long> assigneeIds;
    private Set<Long> tagIds;
    private Long parentTaskId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Set<Long> getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(Set<Long> assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public Set<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
