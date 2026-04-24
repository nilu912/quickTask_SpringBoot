package com.example.quicktask.serviceImpls;

import com.example.quicktask.common.Common;
import com.example.quicktask.common.CommonResponseBean;
import com.example.quicktask.dtos.TeamRequestDTO;
import com.example.quicktask.entity.Team;
import com.example.quicktask.entity.User;
import com.example.quicktask.repository.TeamRepository;
import com.example.quicktask.repository.UserRepository;
import com.example.quicktask.security.JwtService;
import com.example.quicktask.services.TeamService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private  HttpServletRequest request;

//    private EntityManager manager;


    public CommonResponseBean manageTeam(TeamRequestDTO dto) {
        try {

            Team team = new Team();

            if (!Common.isNullAndEmpty(dto.getTeam_id())) {

                Optional<Team> teams = teamRepository.findById(dto.getTeam_id());

                if (teams.isPresent())
                    team = teams.get();

               if(!Common.isNullAndEmpty(dto.getName()))
                   team.setName(dto.getName());

            } else {

                if (Common.isNullAndEmpty(dto.getName()) || Common.isNullAndEmpty(dto.getCreated_by()))
                    return new CommonResponseBean(false, 404, "Parameter is missing", null);

                SecurityContext context = SecurityContextHolder.getContext();
                Authentication auth = context.getAuthentication();
                User authUser = (User) auth.getPrincipal();
                System.out.println(authUser.getU_id());

                // List<User> userList = manager.createQuery("select * from quser where email = " + email).getResultList();
                // String authHeader = request.getHeader("Authentication");
                // String email = jwtService.extractEmail(authHeader.substring(7));
                // Optional<User> user = userRepository.findByEmail(email);

                team.setName(dto.getName());
                team.setCreated_by(authUser.getU_id());
            }

            teamRepository.save(team);
            return new CommonResponseBean(true, 200, "", team);

        } catch (Exception e) {
            System.out.println("{ }"+ e);
        }
        return new CommonResponseBean(false, 500, "Internal server Error", null);
    };

    public CommonResponseBean getAllTeams(TeamRequestDTO dto){
        try {
//            String authHeader = request.getHeader("Authentication");
//            System.out.println(jwtService.extractEmail(authHeader.substring(7)));

            return new CommonResponseBean(true, 200, "", teamRepository.findAll());
        } catch (Exception e) {
            System.out.println("{ }"+ e);
        }
        return new CommonResponseBean(false, 500, "Internal server Error", null);
    }

    public CommonResponseBean deleteTeam(TeamRequestDTO dto) {
        try {
            if(!Common.isNullAndEmpty(dto.getTeam_id()))
                return new CommonResponseBean(false, 404, "parameter is missing", null);

            Optional<Team> team = teamRepository.findById(dto.getTeam_id());
            if(team.isPresent()) {
                teamRepository.deleteById(dto.getTeam_id());
                return new CommonResponseBean(true, 200, "", "no team exist of this team_id");
            }
            else return new CommonResponseBean(false, 404, "no team exist of this team_id", null);
        } catch (Exception e) {
            System.out.println("{ }"+ e);
        }
        return new CommonResponseBean(false, 500, "Internal server Error", null);
    }
}
