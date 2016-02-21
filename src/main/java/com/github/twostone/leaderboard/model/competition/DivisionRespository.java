package com.github.twostone.leaderboard.model.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRespository extends JpaRepository<Division, Long> {

}
