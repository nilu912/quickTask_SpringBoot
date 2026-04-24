package com.example.quicktask.dtos;

import lombok.Data;

@Data
public class TeamMemberRequestDTO {
    private Long u_id, team_id, team_mem_id;
    private String role;
}
