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

    public Stadium(){

    }
    public Stadium(String name, String location, Team team) {
        this.name = name;
        this.location = location;
        this.team = team;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
