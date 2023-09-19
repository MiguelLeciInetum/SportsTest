package com.example.sportsproyect.repository;

import com.example.sportsproyect.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByAbbreviation(String abbreviation);
}
