package com.example.sportsproyect.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "MATCHS")
public class Match {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String round;
    @Column(nullable = false)
    private Date date;
    @OneToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name ="TEAM_HOME", referencedColumnName = "ID")
    private Team teamHome;
    @OneToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name ="TEAM_OUT", referencedColumnName = "ID")
    private Team teamOut;
    @Column(nullable = false)
    private String score;
    public Match(){

    }
    public Match(String round, Date date, Team teamHome, Team teamOut, String score) {
        this.round = round;
        this.date = date;
        this.teamHome = teamHome;
        this.teamOut = teamOut;
        this.score = score;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getTeamHome() {
        return teamHome;
    }

    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    public Team getTeamOut() {
        return teamOut;
    }

    public void setTeamOut(Team teamOut) {
        this.teamOut = teamOut;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
