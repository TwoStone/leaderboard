package com.github.twostone.leaderboard.model.event;

import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, String> {

  @Override
  Iterable<EventType> findAll();

}
