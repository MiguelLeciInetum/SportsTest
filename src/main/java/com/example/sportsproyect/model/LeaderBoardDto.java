package com.example.sportsproyect.model;

import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderBoardDto {
    private Team team;
    private int points;
}
