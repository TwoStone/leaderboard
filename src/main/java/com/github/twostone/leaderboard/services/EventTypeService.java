package com.github.twostone.leaderboard.services;

import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.model.event.EventTypeManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("api/eventtypes")
public class EventTypeService {
  
  private EventTypeManager eventTypeManager;
  
  @Inject
  EventTypeService(EventTypeManager eventTypeManager) {
    super();
    this.eventTypeManager = eventTypeManager;
  }

  @RequestMapping(
      path = "", 
      method = RequestMethod.GET)
  Iterable<EventType> getEventTypes() {
    return this.eventTypeManager.getAll();
  }
}
