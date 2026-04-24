package com.example.quicktask.services;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamRequestDTO;

public interface TeamService {
    CommonResponseBean manageTeam(TeamRequestDTO dto);
    CommonResponseBean getAllTeams(TeamRequestDTO dto);
    CommonResponseBean deleteTeam(TeamRequestDTO dto);
}
