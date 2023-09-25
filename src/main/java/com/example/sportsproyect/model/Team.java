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
    @OneToMany(mappedBy = "teamHome")
    private List<Match> matchHome;
    @OneToMany(mappedBy = "teamOut")
    private List<Match> matchOut;

    public Team(){

    }
    public Team(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
