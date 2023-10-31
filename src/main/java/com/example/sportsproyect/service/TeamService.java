package com.example.sportsproyect.service;

import com.example.sportsproyect.mappers.TeamMapper;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.model.TeamDto;
import com.example.sportsproyect.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
@RequiredArgsConstructor
@Slf4j
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
//    @Autowired
//    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper)
//    {
//        this.teamRepository = teamRepository;
//        this.teamMapper = teamMapper;
//    }
    public List<TeamDto> getAllTeams() {
        return teamMapper.fromTeamDtoTeams(teamRepository.findAll());
    }
    public Team getTeamById(long id) {
        return teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found"));
    }
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }
    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }
    public void deleteAllTeams() {
        teamRepository.deleteAll();
    }

}