package com.github.twostone.leaderboard.model.competition;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends PagingAndSortingRepository<Competition, Long> {

}
