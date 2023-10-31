package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.model.TeamDto;
import com.example.sportsproyect.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("teams")
public class TeamController {
    @Autowired
    TeamService teamService;
    @GetMapping()
    public List<TeamDto> getAllTeams()  {
        return teamService.getAllTeams();
    }
    @GetMapping("{id}")
    public Team getTeamById(@PathVariable("id") long id) {
        return teamService.getTeamById(id);
    }
    @PostMapping()
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }
    @PutMapping()
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }
    @DeleteMapping("{id}")
    public void deleteTeam(@PathVariable("id") long id) {
        teamService.deleteTeam(id);
    }
    @DeleteMapping()
    public void deleteAllTeams() {
        teamService.deleteAllTeams();
    }
}