package com.example.sportsproyect.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "TEAMS")
public class Team {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(nullable = false)
    private String abbreviation;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @OneToOne(mappedBy = "team")
    private Stadium stadium;
    @OneToMany(mappedBy = "teamHome")
    private List<Match> matchHome;
    @OneToMany(mappedBy = "teamOut")
    private List<Match> matchOut;
}
