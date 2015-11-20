package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.athlete.Athlete;

import java.text.MessageFormat;

public class AthleteAlreadyRegisteredException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private Athlete athlete;
  private Competition competition;

  /**
   * Creates a new {@link AthleteAlreadyRegisteredException} Object.
   */
  public AthleteAlreadyRegisteredException(Athlete athlete, Competition competition) {
    super(MessageFormat.format("Athlete {0} is already registered for the competition {1}", athlete,
        competition));
    this.athlete = athlete;
    this.competition = competition;
  }

  public Athlete getAthlete() {
    return this.athlete;
  }

  public Competition getCompetition() {
    return this.competition;
  }
}
