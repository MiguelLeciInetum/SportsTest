package com.example.sportsproyect.service;

import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    @Autowired
    public TeamService(TeamRepository teamRepository)
    {
        this.teamRepository = teamRepository;
    }
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
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