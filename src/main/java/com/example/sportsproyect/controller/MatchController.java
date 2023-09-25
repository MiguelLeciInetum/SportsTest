package com.example.sportsproyect.controller;

import com.example.sportsproyect.model.LeaderBoardDto;
import com.example.sportsproyect.model.Match;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.repository.MatchRepository;
import com.example.sportsproyect.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/matchs")
public class MatchController {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TeamRepository teamRepository;

    @GetMapping()
    public ResponseEntity<List<Match>> getAllMatchs() {
        try {
            List<Match> matchs = new ArrayList<Match>();
            matchRepository.findAll().forEach(matchs::add);
            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(matchs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable("id") long id) {
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            return new ResponseEntity<>(matchData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        try {
            Match _match = matchRepository
                    .save(new Match(match.getRound(), match.getDate(), match.getTeamHome(), match.getTeamOut(), match.getScore()));
            return new ResponseEntity<>(_match, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable("id") long id, @RequestBody Match match) {
        Optional<Match> matchData = matchRepository.findById(id);

        if (matchData.isPresent()) {
            Match _match = matchData.get();
            _match.setRound(match.getRound());
            _match.setDate(match.getDate());
            _match.setTeamHome(match.getTeamHome());
            _match.setTeamOut(match.getTeamOut());
            _match.setScore(match.getScore());
            return new ResponseEntity<>(matchRepository.save(_match), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id") long id) {
        try {
            matchRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllMatchs() {
        try {
            matchRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/play")
    public ResponseEntity<Match> play()
    {
        try {
            Random rand = new Random();
            String result = rand.nextInt(10) + "-" + rand.nextInt(10);
            List<Team> teams = new ArrayList<Team>();
            teamRepository.findAll().forEach(teams::add);
            Match _match = matchRepository
                    .save(new Match(Integer.toString(rand.nextInt(28)),
                            new Date(),
                            teams.remove(rand.nextInt((int)teams.stream().count())),
                            teams.get(rand.nextInt((int)teams.stream().count())),
                            result));
            return new ResponseEntity<>(_match, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/leaderboard")
    public ResponseEntity<List<LeaderBoardDto>> getLeaderBoard()
    {
        try {
            List<Match> matchs = new ArrayList<Match>();
            matchRepository.findAll().forEach(matchs::add);
            if (matchs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<Team> teams = new ArrayList<Team>();
            teamRepository.findAll().forEach(teams::add);
            if (teams.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<LeaderBoardDto> leaderBoardDtos = new ArrayList<LeaderBoardDto>();
            for (Team team: teams)
            {
                int points = 0;
                for (Match match: matchs)
                {
                    if (match.getTeamHome() == team)
                    {
                        int home = Integer.valueOf(match.getScore().substring(0,1));
                        int out = Integer.valueOf(match.getScore().substring(2,3));
                        if (home > out) points = points+3;
                        if (home == out) points++;
                    }
                    if (match.getTeamOut() == team)
                    {
                        int home = Integer.valueOf(match.getScore().substring(0,1));
                        int out = Integer.valueOf(match.getScore().substring(2,3));
                        if (home < out) points = points+3;
                        if (home == out) points++;
                    }
                }
                leaderBoardDtos.add(new LeaderBoardDto(team,points));
            }
            return new ResponseEntity<>(leaderBoardDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}