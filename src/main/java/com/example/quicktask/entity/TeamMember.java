package com.example.quicktask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "team_members")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long team_mem_id;

    private Long u_id, team_id;
    private String role;
}
