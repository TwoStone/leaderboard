package com.github.twostone.leaderboard.model.ranking;

import com.github.twostone.leaderboard.model.competition.Competitor;

import java.util.List;

public class RankedCompetitionScore implements Comparable<RankedCompetitionScore> {
  
  private Competitor competitor;
  private List<RankedEventScore> eventScores;
  private int rank;
  
  RankedCompetitionScore(Competitor competitor, List<RankedEventScore> eventScores) {
    super();
    this.competitor = competitor;
    this.eventScores = eventScores;
  }
  
  public long getScore() {
    return this.eventScores.stream().mapToLong(RankedEventScore::getRank).sum();
  }

  public Competitor getCompetitor() {
    return this.competitor;
  }

  public List<RankedEventScore> getEventScores() {
    return this.eventScores;
  }
  
  public int getRank() {
    return this.rank;
  }
  
  public void setRank(int rank) {
    this.rank = rank;
  }

  @Override
  public int compareTo(RankedCompetitionScore o) {
    return (int) (this.getScore() - o.getScore());
  }
  
}
