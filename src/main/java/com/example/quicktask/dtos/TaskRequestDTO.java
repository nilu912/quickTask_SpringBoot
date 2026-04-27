package com.example.quicktask.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDTO {
    private String title, description;
    private Integer status, priority;
    private Long created_by, assigned_to, team_id;

    private LocalDateTime due_date;
}
