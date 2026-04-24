package com.example.quicktask.serviceImpls;

import com.example.quicktask.common.Common;
import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamMemberRequestDTO;
import com.example.quicktask.entity.Team;
import com.example.quicktask.entity.TeamMember;
import com.example.quicktask.repository.TeamMemberRepository;
import com.example.quicktask.services.TeamMemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    private EntityManager manager;

    public CommonResponseBean createTeamMember(TeamMemberRequestDTO dto) {
        try {
            System.out.println(dto);
            TeamMember teamMember = new TeamMember();
            if (!Common.isNullAndEmpty(dto.getTeam_mem_id())) {
                if (!Common.isNullAndEmpty(dto.getTeam_id()))
                    teamMember.setTeam_id(dto.getTeam_id());
                if (!Common.isNullAndEmpty(dto.getU_id()))
                    teamMember.setU_id(dto.getU_id());
                if (!Common.isNullAndEmpty(dto.getRole()))
                    teamMember.setRole(dto.getRole());
            } else{
                if (Common.isNullAndEmpty(dto.getTeam_id()) || Common.isNullAndEmpty(dto.getRole()) || Common.isNullAndEmpty(dto.getU_id()))
                    return new CommonResponseBean(false, 404, "Parameter is missing", null);

                teamMember.setU_id(dto.getU_id());
                teamMember.setTeam_id(dto.getTeam_id());
                teamMember.setRole(dto.getRole());
            }
//            List existingTM = manager
//                    .createNativeQuery("select * from team_members tm where tm.team_id = 1 and tm.u_id = 1")
//                    .getResultList();

            teamMemberRepository.save(teamMember);

            return new CommonResponseBean(true, 200, "", teamMember);

        } catch (Exception e) {
            System.out.println("{ }" + e);
        }
        return new CommonResponseBean(false, 500, "Internal server error", null);
    }

    public CommonResponseBean getAllTeamMemberByTeam(TeamMemberRequestDTO dto) {
        try {
            System.out.println(dto);
            return new CommonResponseBean(true, 200, "", teamMemberRepository.findAll());
        } catch (Exception e) {
            System.out.println("{ }" + e);
        }
        return new CommonResponseBean(false, 500, "Internal server error", null);
    }

    public CommonResponseBean deleteTeamMemberById(TeamMemberRequestDTO dto)  {
        try {
            System.out.println(dto);
        } catch (Exception e) {
            System.out.println("{ }" + e);
        }
        return new CommonResponseBean(false, 500, "Internal server error", null);
    }
}
