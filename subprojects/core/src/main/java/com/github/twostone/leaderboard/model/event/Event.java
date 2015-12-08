package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionParticipation;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

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

  @ManyToOne
  private EventType type;

  @OneToMany(mappedBy = "event")
  private Collection<EventParticipation> participants;

  @ManyToOne
  private Competition competition;

  protected Event() {
    super();
    this.participants = new ArrayList<>();
  }

  /**
   * Creates a new {@link Event} Object.
   */
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

  public Collection<EventParticipation> getParticipants() {
    return this.participants;
  }

  /**
   * Sets the type of the event.
   * This action is only possible if no result has been logged for the event.
   */
  public void setEventType(EventType type) {
    if (!this.participants.isEmpty()) {
      throw new RuntimeException(
          "Not able to change event type, because there are already logged results.");
    }
    this.type = type;
  }

  /**
   * Creates a new {@link EventParticipation participation} for the provided competitor.
   * @throws RuntimeException if there is already a participation for the competitor in this event.
   */
  public void addParticipation(CompetitionParticipation participation) {
    EventParticipation eventParticipation = new EventParticipation(this, participation);
    if (this.participants.contains(eventParticipation)) {
      throw new RuntimeException(
          MessageFormat.format("The athlete {0} already participated in event {1}.",
              participation.getParticipant(), this));
    }
    this.participants.add(eventParticipation);
  }

  public void removeParticipation(EventParticipation participation) {
    this.participants.remove(participation);
  }
}
