package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.event.Event;

import java.io.Serializable;
import java.text.MessageFormat;
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

  @OneToMany(mappedBy = "competition")
  private Collection<CompetitionParticipation> participations;

  @OneToMany(mappedBy = "competition")
  private List<Event> events;

  protected Competition() {
    super();
    this.divisions = new ArrayList<>();
    this.participations = new ArrayList<>();
    this.events = new ArrayList<>();
  }

  /**
   * Creates a new {@link Competition} Object.
   */
  public Competition(String name, LocalDate date) {
    this();
    this.name = name;
    this.date = date;
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

  public Collection<CompetitionParticipation> getParticipations() {
    return this.participations;
  }

  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Registers the athlete for the competition.
   */
  public CompetitionParticipation registerAthlete(Athlete athlete, Division division) {
    if (!this.divisions.contains(division)) {
      throw new RuntimeException(MessageFormat.format(
          "Cannot register athlete {0} for division {1} in competition {2}, "
          + "because the division does not belong to the competition",
          athlete, division, this));
    }
    
    CompetitionParticipation participation = new CompetitionParticipation(athlete, division, this);
    if (this.participations.contains(participation)) {
      throw new AthleteAlreadyRegisteredException(athlete, this);
    }

    this.participations.add(participation);
    return participation;
  }

  /**
   * Adds the division to the competition.
   */
  public Division addDivision(Division division) {
    if (this.divisions.contains(division)) {
      throw new RuntimeException(MessageFormat.format(
          "The divison {0} has already been added to the competition {1}.", division, this));
    }
    this.divisions.add(division);
    return division;
  }

  /**
   * Removes the division from the competition.
   * This action is only possible, if no athlete is registered for the division.
   */
  public void removeDivision(Division division) {
    if (this.participations.stream().anyMatch(p -> p.getDivision().equals(division))) {
      throw new RuntimeException(
          MessageFormat.format(
              "Cannot remove division {0} because there are already athletes registered for", 
              division));
    }
    this.divisions.remove(division);
  }

  public Event addEvent(Event event) {
    this.events.add(event);
    return event;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public void removeEvent(Event event) {
    this.events.remove(event);
  }
}
