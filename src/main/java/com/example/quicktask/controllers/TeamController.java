package com.example.quicktask.controllers;

import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamRequestDTO;
import com.example.quicktask.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/createteam")
    public CommonResponseBean createTeam(@RequestBody TeamRequestDTO dto) {
        return teamService.manageTeam(dto);
    }
    @PostMapping("/getallteams")
    public CommonResponseBean getAllTeams(@RequestBody TeamRequestDTO dto) {
        return teamService.getAllTeams(dto);
    }
    @PostMapping("/deleteteam")
    public CommonResponseBean deleteTeam(@RequestBody TeamRequestDTO dto) {
        return teamService.deleteTeam(dto);
    }

}
