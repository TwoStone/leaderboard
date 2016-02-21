package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.event.EventType;

public class NewEventRequest {
  String name;
  String description;
  EventType type;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EventType getType() {
    return this.type;
  }

  public void setType(EventType type) {
    this.type = type;
  }
}