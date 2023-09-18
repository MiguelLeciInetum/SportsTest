package com.example.sportsproyect.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Lazy
@Table(name = "TEAMS")
public class Team {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String abbreviation;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    @OneToOne(mappedBy = "team")
    private Stadium stadium;
}
