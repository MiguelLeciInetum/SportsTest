package com.example.sportsproyect.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "PLAYERS")
public class Player {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(nullable = false)
    private String surname;
    @NonNull
    @Column(nullable = false)
    private String nationality;
    @NonNull
    @ManyToOne
    @JoinColumn(name ="TEAM_ID", referencedColumnName = "ID")
    private Team team;
}