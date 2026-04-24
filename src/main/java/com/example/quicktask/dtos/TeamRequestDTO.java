package com.example.quicktask.dtos;

import lombok.Data;

@Data
public class TeamRequestDTO {
    private Long team_id;
    private String name;
    private Integer created_by;
}
