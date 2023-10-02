package com.example.sportsproyect.service;

import com.example.sportsproyect.model.LeaderBoardRequest;
import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.repository.MatchRepository;
import com.example.sportsproyect.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        // return matchRepository.findById(id).orElse(new Match());
        // return matchRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Match not found"));

    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Match updateMatch(Match match) {
        return matchRepository.save(match);
    }
    public void deleteMatch(long id) {
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
                        teams.remove(rand.nextInt(teams.size())),
                        teams.get(rand.nextInt(teams.size())),
                        rand.nextInt(10) + "-" + rand.nextInt(10)
                )
        );
    }

    public List<LeaderBoardRequest> getLeaderBoard() {
        List<Match> matchs = matchRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        List<LeaderBoardRequest> leaderBoardDtos = new ArrayList<LeaderBoardRequest>();
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
            leaderBoardDtos.add(new LeaderBoardRequest(team, points));
        }
        return leaderBoardDtos;
    }
}