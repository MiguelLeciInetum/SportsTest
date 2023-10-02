package com.example.sportsproyect.model;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class LeaderBoardRequest {
    private Team team;
    private int points;
}
