package com.github.twostone.leaderboard.model.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EventTypeRepository extends CrudRepository<EventType, String> {

  @Override
  Iterable<EventType> findAll();

}
