package com.example.sportsproyect.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@ToString
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

    @JsonIgnore
    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @JsonIgnore
    @OneToOne(mappedBy = "team")
    private Stadium stadium;

    @JsonIgnore
    @OneToMany(mappedBy = "teamHome")
    private List<Match> matchHome;

    @OneToMany(mappedBy = "teamOut")
    @JsonIgnore
    private List<Match> matchOut;
}
