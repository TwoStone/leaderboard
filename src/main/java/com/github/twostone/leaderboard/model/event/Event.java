package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Event extends AbstractEntity {

  private String name;
  private String description;
  @ManyToOne
  private EventType type;
  
  Event() {
    super();
  }

  /**
   * Creates a new {@link Event} object.
   */
  public Event(String name, String description, EventType type) {
    super();
    this.name = name;
    this.description = description;
    this.type = type;
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
  
  public void setType(EventType type) {
    this.type = type;
  }
}
