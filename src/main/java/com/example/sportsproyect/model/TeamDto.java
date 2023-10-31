package com.example.sportsproyect.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class TeamDto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String abbreviation;
//    private List<Player> players;
//    private Stadium stadium;
//    private List<Match> matchHome;
//    private List<Match> matchOut;
}
