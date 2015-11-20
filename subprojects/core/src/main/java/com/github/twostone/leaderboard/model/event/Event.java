package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Event.
 */
@Entity
public class Event extends AbstractEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private String description;

  @ManyToOne(cascade = CascadeType.ALL)
  private EventType type;

  @OneToMany
  private Collection<Result> results;

  @ManyToOne
  private Competition competition;

  protected Event() {
    super();
    this.results = new ArrayList<>();
  }

  public Event(String name, EventType type) {
    super();
    this.name = name;
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

  public Collection<Result> getResults() {
    return this.results;
  }

}
