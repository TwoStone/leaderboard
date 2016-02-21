package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.event.Event;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Competition extends AbstractEntity {
  
  private String name;
  
  @OneToMany(fetch = FetchType.EAGER)
  private Set<Division> divisions;
  
  @OneToMany(fetch = FetchType.EAGER)
  private Set<Competitor> competitors;
  
  @OneToMany(fetch = FetchType.EAGER)
  private Set<Event> events;
  
  Competition() {
    super();
    this.divisions = new LinkedHashSet<>();
    this.competitors = new LinkedHashSet<>();
    this.events = new LinkedHashSet<>();
  }
  
  public Competition(String name) {
    this();
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public void addDivision(Division division) {
    this.divisions.add(division);
  }

  public Set<Division> getDivisions() {
    return this.divisions;
  }

  public void addRegistration(Competitor registration) {
    this.competitors.add(registration);
  }
  
  public Set<Competitor> getCompetitors() {
    return this.competitors;
  }
  
  public void addEvent(Event event) {
    this.events.add(event);
  }
  
  public Set<Event> getEvents() {
    return this.events;
  }
}
