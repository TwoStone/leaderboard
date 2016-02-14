package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.event.EventType.Ordering;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class EventTypeManager {

  private EventTypeRepository repository;

  @Inject
  EventTypeManager(EventTypeRepository repository) {
    super();
    this.repository = repository;
  }
  
  /**
   * Initializes the manager.
   */
  public void init() {
    Iterable<EventType> types = this.repository.findAll(Lists.newArrayList(0L, 1L));
    if (Iterables.isEmpty(types)) {
      this.repository.save(Lists.newArrayList(
            new EventType("Time", Ordering.ASCENDING),
            new EventType("Points", Ordering.DESCENDING)
          ));
    }
  }
  
  public Iterable<EventType> getAll() {
    return this.repository.findAll();
  }

  public EventType findOne(long id) {
    return this.repository.findOne(id);
  }
  
}
