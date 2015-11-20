package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.event.Event;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Competition.
 */
@Entity
public class Competition extends AbstractEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private LocalDate date;

  @OneToMany(cascade = CascadeType.ALL)
  private Collection<Division> divisions;

  @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
  private Collection<Participation> participations;

  @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
  private List<Event> events;

  protected Competition() {
    super();
  }

  /**
   * Creates a new {@link Competition} Object.
   */
  public Competition(String name, LocalDate date) {
    super();
    this.name = name;
    this.date = date;
    this.divisions = new ArrayList<>();
    this.participations = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Collection<Division> getDivisions() {
    return this.divisions;
  }

  public Collection<Participation> getParticipations() {
    return this.participations;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Registers the athlete for the competition.
   */
  public Participation registerAthlete(Athlete athlete, Division division) {
    Participation participation = new Participation(athlete, division, this);
    if (this.participations.contains(participation)) {
      throw new AthleteAlreadyRegisteredException(athlete, this);
    }

    this.participations.add(participation);
    return participation;
  }

  public Division addDivision(Division division) {
    this.divisions.add(division);
    return division;
  }

  public Event addEvent(Event event) {
    this.events.add(event);
    return event;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
