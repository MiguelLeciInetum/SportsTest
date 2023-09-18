package com.example.sportsproyect.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String nationality;
    @ManyToOne
    @JoinColumn(name ="TEAM_ID", referencedColumnName = "ID")
    private Team team;
}