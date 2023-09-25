package com.example.sportsproyect.model;

import java.util.Date;

public class LeaderBoardDto {
    private Team team;
    private int points;
    public LeaderBoardDto(){

    }
    public LeaderBoardDto(Team team, int points){
        this.team = team;
        this.points=points;
    }
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
