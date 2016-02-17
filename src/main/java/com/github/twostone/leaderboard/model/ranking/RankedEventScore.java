package com.github.twostone.leaderboard.model.ranking;

import com.github.twostone.leaderboard.model.score.Score;

public class RankedEventScore {

  private Score score;
  private int rank;
  
  RankedEventScore(Score score, int rank) {
    super();
    this.score = score;
    this.rank = rank;
  }

  public Score getScore() {
    return this.score;
  }
  
  public int getRank() {
    return this.rank;
  }
}
