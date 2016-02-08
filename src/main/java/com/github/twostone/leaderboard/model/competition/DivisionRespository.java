package com.github.twostone.leaderboard.model.competition;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRespository extends CrudRepository<Division, Long> {

}
