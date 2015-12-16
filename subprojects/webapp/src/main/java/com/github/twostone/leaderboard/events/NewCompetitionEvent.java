package com.github.twostone.leaderboard.events;

import com.github.twostone.leaderboard.model.competition.Competition;

public class NewCompetitionEvent {

  private Competition competition;

  public NewCompetitionEvent(Competition competition) {
    this.competition = competition;
  }

  public Competition getCompetition() {
    return this.competition;
  }

}
