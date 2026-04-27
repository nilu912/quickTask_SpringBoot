package com.example.quicktask.serviceImpls;

import com.example.quicktask.common.Common;
import com.example.quicktask.common.CommonEnum;
import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamMemberRequestDTO;
import com.example.quicktask.entity.Team;
import com.example.quicktask.entity.TeamMember;
import com.example.quicktask.entity.User;
import com.example.quicktask.repository.TeamMemberRepository;
import com.example.quicktask.repository.TeamRepository;
import com.example.quicktask.repository.UserRepository;
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

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    private EntityManager manager;

    public CommonResponseBean createTeamMember(TeamMemberRequestDTO dto) {
        try {
            System.out.println(dto);

            TeamMember teamMember = new TeamMember();

            if (!Common.isNullAndEmpty(dto.getTeam_mem_id())) {
                Optional<TeamMember> existedTeamMember = teamMemberRepository.findById(dto.getTeam_mem_id());

                if(existedTeamMember.isPresent())
                    teamMember = existedTeamMember.get();
                else
                    return new CommonResponseBean(false, CommonEnum.NO_DATA_FOUND.getKey(), "TeamMember not found", null);

                if (!Common.isNullAndEmpty(dto.getTeam_id())) {
                    Optional<Team> teamres = teamRepository.findById(dto.getTeam_id());
                    if(teamres.isPresent())
                        teamMember.setTeam_id(dto.getTeam_id());
                    else
                        return new CommonResponseBean(false, CommonEnum.NO_DATA_FOUND.getKey(), "Team_id not found", null);
                }
                if (!Common.isNullAndEmpty(dto.getU_id())) {
                    Optional<User> userres = userRepository.findById(dto.getU_id());
                    if(userres.isPresent())
                        teamMember.setU_id(dto.getU_id());
                    else
                        return new CommonResponseBean(false,  CommonEnum.NO_DATA_FOUND.getKey(), "User not found", null);
                }
                if (!Common.isNullAndEmpty(dto.getRole()))
                    teamMember.setRole(dto.getRole());
            } else{
                if (Common.isNullAndEmpty(dto.getTeam_id()) || Common.isNullAndEmpty(dto.getU_id()))
                    return new CommonResponseBean(false,  CommonEnum.PARAMETER_MISSING.getKey(), CommonEnum.PARAMETER_MISSING.getValue(), null);

                Optional<Team> teamres = teamRepository.findById(dto.getTeam_id());
                if(teamres.isPresent())
                    teamMember.setTeam_id(dto.getTeam_id());
                else
                    return new CommonResponseBean(false,  CommonEnum.NO_DATA_FOUND.getKey(), "Team_id not found", null);

                Optional<User> userres = userRepository.findById(dto.getU_id());
                if(userres.isPresent())
                    teamMember.setU_id(dto.getU_id());
                else
                    return new CommonResponseBean(false, CommonEnum.NO_DATA_FOUND.getKey(), "User not found", null);

                if(!Common.isNullAndEmpty(dto.getRole()))
                    teamMember.setRole(dto.getRole());
                else teamMember.setRole(CommonEnum.USER.getValue());

            }
//            List existingTM = manager
//                    .createNativeQuery("select * from team_members tm where tm.team_id = 1 and tm.u_id = 1")
//                    .getResultList();

            teamMemberRepository.save(teamMember);

            return new CommonResponseBean(true, CommonEnum.SUCCESS.getKey(), "", teamMember);

        } catch (Exception e) {
            System.out.println("{ }" + e);
        }
        return new CommonResponseBean(false, CommonEnum.INTERNAL_SERVER_ERROR.getKey(), CommonEnum.INTERNAL_SERVER_ERROR.getValue(), null);
    }

    public CommonResponseBean getAllTeamMemberByTeam(TeamMemberRequestDTO dto) {
        try {
            System.out.println(dto);
            return new CommonResponseBean(true, CommonEnum.SUCCESS.getKey(), "", teamMemberRepository.findAll());
        } catch (Exception e) {
            System.out.println("{ }" + e);
        }
        return new CommonResponseBean(false, CommonEnum.INTERNAL_SERVER_ERROR.getKey(), CommonEnum.INTERNAL_SERVER_ERROR.getValue(), null);
    }

    public CommonResponseBean deleteTeamMemberById(TeamMemberRequestDTO dto)  {
        try {
            System.out.println(dto);
            if(Common.isNullAndEmpty(dto.getTeam_mem_id()))
                return new CommonResponseBean(false, CommonEnum.PARAMETER_MISSING.getKey(), CommonEnum.PARAMETER_MISSING.getValue(), null);
            Optional<TeamMember> teamMember = teamMemberRepository.findById(dto.getTeam_mem_id());
            if(teamMember.isEmpty())
                return new CommonResponseBean(false, CommonEnum.NO_DATA_FOUND.getKey(), CommonEnum.NO_DATA_FOUND.getValue(), null);
            teamMemberRepository.deleteById(dto.getTeam_mem_id());
            return new CommonResponseBean(true, CommonEnum.DELETED.getKey(), CommonEnum.DELETED.getValue(), null);
        } catch (Exception e) {
            System.out.println("{ }" + e);
        }
        return new CommonResponseBean(false, CommonEnum.INTERNAL_SERVER_ERROR.getKey(), CommonEnum.INTERNAL_SERVER_ERROR.getValue(), null);
    }
}
