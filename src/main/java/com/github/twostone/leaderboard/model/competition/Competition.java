package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.event.Event;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Competition extends AbstractEntity {
  
  private String name;
  
  @OneToMany
  private List<Division> divisions;
  
  @OneToMany(fetch = FetchType.EAGER)
  private List<Competitor> competitors;
  
  @OneToMany
  private List<Event> events;
  
  Competition() {
    super();
    this.divisions = new ArrayList<>();
    this.competitors = new ArrayList<>();
    this.events = new ArrayList<>();
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

  public List<Division> getDivisions() {
    return this.divisions;
  }

  public void addRegistration(Competitor registration) {
    this.competitors.add(registration);
  }
  
  public List<Competitor> getCompetitors() {
    return this.competitors;
  }
  
  public void addEvent(Event event) {
    this.events.add(event);
  }
  
  public List<Event> getEvents() {
    return this.events;
  }
}
