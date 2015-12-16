package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.athlete.Athlete;

import javax.persistence.Entity;

@Entity
public class IndividualRegistration extends CompetitionRegistration {

  private static final long serialVersionUID = 1L;

  private Athlete athlete;

  protected IndividualRegistration(Athlete athlete, Competition competition, Division division) {
    super(competition, division);
  }

  public Athlete getAthlete() {
    return this.athlete;
  }
}
