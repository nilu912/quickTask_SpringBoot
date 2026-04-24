package com.example.quicktask.services;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamMemberRequestDTO;

public interface TeamMemberService {
    CommonResponseBean createTeamMember(TeamMemberRequestDTO dto);
    CommonResponseBean getAllTeamMemberByTeam(TeamMemberRequestDTO dto);
    CommonResponseBean deleteTeamMemberById(TeamMemberRequestDTO dto);
}
