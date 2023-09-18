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
    @Column(nullable = false)
    private String teamHome;
    @Column(nullable = false)
    private String teamOut;
    @Column(nullable = false)
    private String score;
}
