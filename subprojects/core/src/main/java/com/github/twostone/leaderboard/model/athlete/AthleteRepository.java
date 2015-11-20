package com.github.twostone.leaderboard.model.athlete;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/athlete")
public interface AthleteRepository extends CrudRepository<Athlete, String> {
}
