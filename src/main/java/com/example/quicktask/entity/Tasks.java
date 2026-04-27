package com.example.quicktask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    @CreationTimestamp
    private LocalDateTime created_at;

    private String title, description;
    private Integer status, priority;
    private Long created_by, assigned_to, team_id;

    private LocalDateTime due_date;
}
