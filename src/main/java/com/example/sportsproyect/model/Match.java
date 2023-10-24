package com.example.sportsproyect.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@ToString
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "MATCHS")
public class Match {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NonNull
    @Column(nullable = false)
    private String round;
    @NonNull
    @Column(nullable = false)
    private Date date;
    @NonNull
    @ManyToOne()
    @JoinColumn(name ="TEAM_HOME", referencedColumnName = "ID")
    private Team teamHome;
    @NonNull
    @ManyToOne()
    @JoinColumn(name ="TEAM_OUT", referencedColumnName = "ID")
    private Team teamOut;
    @NonNull
    @Column(nullable = false)
    private String score;
}
