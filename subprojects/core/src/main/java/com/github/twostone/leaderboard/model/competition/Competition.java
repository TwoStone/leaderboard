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
  private List<CompetitionRegistration> registrations;
  
  @OneToMany
  private List<Event> events;
  
  Competition() {
    super();
    this.divisions = new ArrayList<>();
    this.registrations = new ArrayList<>();
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

  public void addRegistration(CompetitionRegistration registration) {
    this.registrations.add(registration);
  }
  
  public List<CompetitionRegistration> getRegistrations() {
    return this.registrations;
  }
  
  public void addEvent(Event event) {
    this.events.add(event);
  }
  
  public List<Event> getEvents() {
    return this.events;
  }
}
