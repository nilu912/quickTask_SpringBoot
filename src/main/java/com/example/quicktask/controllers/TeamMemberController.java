package com.example.quicktask.controllers;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamMemberRequestDTO;
import com.example.quicktask.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team_meamber")
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @PostMapping("/createteammember")
    public CommonResponseBean createTeamMember(@RequestBody TeamMemberRequestDTO dto) {
        return teamMemberService.createTeamMember(dto);
    }

    @PostMapping("/getteammembermyteam")
    public CommonResponseBean getAllTeamMemberByTeam(@RequestBody TeamMemberRequestDTO dto) {
        return teamMemberService.getAllTeamMemberByTeam(dto);
    }
    @PostMapping("/deleteteammember")
    public CommonResponseBean deleteTeamMemberById(@RequestBody TeamMemberRequestDTO dto) {
        return teamMemberService.deleteTeamMemberById(dto);
    }
}
