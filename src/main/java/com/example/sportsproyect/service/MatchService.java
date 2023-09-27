package com.example.sportsproyect.service;

import com.example.sportsproyect.model.LeaderBoardDto;
import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.repository.MatchRepository;
import com.example.sportsproyect.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Service
public class MatchService {

    private Random rand = new Random();
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    @Autowired
    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository)
    {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }
    public List<Match> getAllMatchs() {
            return matchRepository.findAll();
    }

    public Match getMatchById(long id) {
        return matchRepository.findById(id).get();
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Match match) {
        return matchRepository.save(match);
    }
    public void deleteMatch(@PathVariable("id") long id) {
        matchRepository.deleteById(id);
    }

    public void deleteAllMatchs() {
        matchRepository.deleteAll();
    }

    public Match play()
    {
        List<Team> teams = teamRepository.findAll();
        return matchRepository.save(
                new Match(
                        Integer.toString(rand.nextInt(28)),
                        new Date(),
                        teams.remove(rand.nextInt((int)teams.stream().count())),
                        teams.get(rand.nextInt((int)teams.stream().count())),
                        rand.nextInt(10) + "-" + rand.nextInt(10)
                )
        );
    }

    public List<LeaderBoardDto> getLeaderBoard() {
        List<Match> matchs = matchRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        List<LeaderBoardDto> leaderBoardDtos = new ArrayList<LeaderBoardDto>();
        for (Team team : teams) {
            int points = 0;
            for (Match match : matchs) {
                if (match.getTeamHome() == team) {
                    int home = Integer.valueOf(match.getScore().substring(0, 1));
                    int out = Integer.valueOf(match.getScore().substring(2, 3));
                    if (home > out) points = points + 3;
                    if (home == out) points++;
                }
                if (match.getTeamOut() == team) {
                    int home = Integer.valueOf(match.getScore().substring(0, 1));
                    int out = Integer.valueOf(match.getScore().substring(2, 3));
                    if (home < out) points = points + 3;
                    if (home == out) points++;
                }
            }
            leaderBoardDtos.add(new LeaderBoardDto(team, points));
        }
        return leaderBoardDtos;
    }
}