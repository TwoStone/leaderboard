package com.github.twostone.leaderboard.model.event;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface EventRepository extends Repository<Event, String> {

  Event findOne(String id);

  Collection<Event> findByCompetitionId(String competitionId);
}
