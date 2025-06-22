package org.backend.enums;

public enum NotificationType {
    TASK_COMPLETED,  // Công việc đã hoàn thành
    TASK_PENDING,    // Công việc bị trì hoãn
    NEW_TASK_ASSIGNED, // Công việc mới được phân công
    USER_ADDED,      // Người dùng được thêm vào nhóm
    USER_REMOVED     // Người dùng bị loại khỏi nhóm
}