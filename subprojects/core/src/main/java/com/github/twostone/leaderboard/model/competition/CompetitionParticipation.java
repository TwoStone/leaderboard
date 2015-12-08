package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.athlete.Athlete;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CompetitionParticipation extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne
  private Athlete participant;

  @ManyToOne
  private Competition competition;

  @ManyToOne
  private Division division;

  protected CompetitionParticipation() {
    super();
  }

  CompetitionParticipation(Athlete athlete, Division division, Competition competition) {
    super();
    this.participant = athlete;
    this.division = division;
    this.competition = competition;
  }

  public Athlete getParticipant() {
    return participant;
  }

  public Division getDivision() {
    return division;
  }

  public Competition getCompetition() {
    return competition;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof CompetitionParticipation) {
      CompetitionParticipation other = (CompetitionParticipation) obj;
      return this.participant.equals(other.participant)
          && this.competition.equals(other.competition);
    }

    return false;
  }

}
