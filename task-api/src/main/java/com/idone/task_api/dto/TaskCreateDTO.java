package com.idone.task_api.dto;

import java.time.LocalDateTime;

import com.idone.task_api.entity.TaskPriority;

public class TaskCreateDTO {

    private String title;
    private String description;
    private TaskPriority priority;
    private LocalDateTime finDate;

    public TaskCreateDTO() {
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
