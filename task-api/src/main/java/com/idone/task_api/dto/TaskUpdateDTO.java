package com.idone.task_api.dto;

import java.time.LocalDateTime;

import com.idone.task_api.entity.TaskPriority;
import com.idone.task_api.entity.TaskStatus;

public class TaskUpdateDTO {
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime finDate;
    
    public TaskUpdateDTO() {
    }

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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public LocalDateTime getFinDate() {
        return finDate;
    }

    public void setFinDate(LocalDateTime finDate) {
        this.finDate = finDate;
    }

    
}
