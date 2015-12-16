package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CompetitionRegistration extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne
  private Competition competition;
  private Division division;

  protected CompetitionRegistration(Competition competition, Division division) {
    super();
    this.competition = competition;
    this.division = division;
  }

  public Competition getCompetition() {
    return this.competition;
  }

  public Division getDivision() {
    return this.division;
  }

  public void setDivision(Division division) {
    this.division = division;
  }
}
