package com.example.sportsproyect.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "STADIUMS")
public class Stadium {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    @NonNull
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(nullable = false)
    private String location;
    @NonNull
    @OneToOne
    @JoinColumn(name ="TEAM_ID", referencedColumnName = "ID")
    private Team team;
}
