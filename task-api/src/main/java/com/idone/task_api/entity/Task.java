package com.idone.task_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Task {

    private Long taskId;

    private String title;

    private String description;

    private LocalDateTime createAt = LocalDateTime.now();

    private LocalDateTime updateAt = LocalDateTime.now();

    private LocalDateTime finDate;
    
}
