package com.example.sportsproyect.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "STADIUMS")
public class Stadium {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;
    @OneToOne
    @JoinColumn(name ="TEAM_ID", referencedColumnName = "ID")
    private Team team;
}
