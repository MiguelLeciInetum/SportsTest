package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.LeaderBoardRequest;
import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("matchs")
public class MatchController {

    private final MatchService matchService;

    @GetMapping()
    public List<Match> getAllMatchs() {
        return matchService.getAllMatchs();
    }

    @GetMapping("{id}")
    public Match getMatchById(@PathVariable("id") long id) {
        return matchService.getMatchById(id);
    }

    @PostMapping()
    public Match createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }

    @PutMapping()
    public Match updateMatch(@RequestBody Match match) {
        return matchService.updateMatch(match);
    }

    @DeleteMapping("{id}")
    public void deleteMatch(@PathVariable("id") long id) {
        matchService.deleteMatch(id);
    }

    @DeleteMapping()
    public void deleteAllMatchs() {
        matchService.deleteAllMatchs();
    }

    @PostMapping("play")
    public Match play() {
        return matchService.play();
    }

    @GetMapping("leaderboard")
    public List<LeaderBoardRequest> getLeaderBoard() {
        return matchService.getLeaderBoard();
    }
}