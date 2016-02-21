package com.github.twostone.leaderboard.model.score;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.event.Event;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Score extends AbstractEntity {
  
  @ManyToOne
  private Event event;
  
  @ManyToOne
  private Competitor competitor;
  
  private Long score;
  
  Score() {
  }
  
  /**
   * Creates a new score.
   */
  public Score(Event event, Competitor competitor, Long value) {
    super();
    this.event = event;
    this.competitor = competitor;
    this.score = value;
  }

  public Event getEvent() {
    return this.event;
  }

  public Competitor getCompetitor() {
    return this.competitor;
  }
  
  public Long getScore() {
    return this.score;
  }
  
  public void setScore(Long value) {
    this.score = value;
  }

  /**
   * Returns true if and only if this score has the same score as otherScore.
   */
  public boolean isSameScore(Score otherScore) {
    if (this.score == null) {
      return otherScore.score == null;
    }
    return this.score.equals(otherScore.score);
  }

  public boolean isNotSet() {
    return this.score == null;
  }
  
}
