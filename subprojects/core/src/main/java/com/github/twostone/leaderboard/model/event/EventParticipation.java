package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.CompetitionParticipation;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EventParticipation extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne
  private Event event;

  @ManyToOne
  private CompetitionParticipation participant;

  private String result;

  protected EventParticipation() {
    super();
  }

  EventParticipation(Event event, CompetitionParticipation participant) {
    this();
    this.event = event;
    this.participant = participant;
  }

  public Event getEvent() {
    return event;
  }

  public CompetitionParticipation getParticipant() {
    return participant;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof EventParticipation) {
      return ((EventParticipation) other).getEvent().equals(this.event)
          && ((EventParticipation) other).getParticipant().equals(this.participant);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.event.hashCode() ^ this.participant.hashCode();
  }
}
