package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Event extends AbstractEntity {

  private String name;
  
  Event() {
    super();
  }

  public Event(String name) {
    super();
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
}
