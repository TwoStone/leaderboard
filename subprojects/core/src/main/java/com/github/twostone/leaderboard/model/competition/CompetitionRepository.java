package com.github.twostone.leaderboard.model.competition;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompetitionRepository extends PagingAndSortingRepository<Competition, String> {

}
