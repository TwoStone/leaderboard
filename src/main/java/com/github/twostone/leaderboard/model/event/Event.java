package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@SuppressWarnings("serial")
public class Event extends AbstractEntity {

  private String name;
  private String description;
  private boolean scalable;
  
  @Enumerated(EnumType.STRING)
  private EventType type;
  
  Event() {
    super();
  }

  /**
   * Creates a new {@link Event} object.
   */
  public Event(String name, String description, EventType type, boolean scalable) {
    super();
    this.name = name;
    this.description = description;
    this.type = type;
    this.scalable = scalable;
  }
  
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
  
  public boolean isScalable() {
    return scalable;
  }
}
