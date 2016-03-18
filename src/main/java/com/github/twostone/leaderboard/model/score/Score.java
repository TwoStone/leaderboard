package com.github.twostone.leaderboard.model.score;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.event.Event;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Score extends AbstractEntity implements Comparable<Score> {
  
  @ManyToOne
  private Event event;
  
  @ManyToOne
  private Competitor competitor;
  
  private Long score;
  
  private boolean scaled;
  
  Score() {
  }
  
  /**
   * Creates a new score.
   */
  public Score(Event event, Competitor competitor, Long value, boolean scaled) {
    super();
    this.event = event;
    this.competitor = competitor;
    this.score = value;
    this.scaled = scaled;
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
  
  public boolean isScaled() {
    return scaled;
  }
  
  public void setScaled(boolean scaled) {
    this.scaled = scaled;
  }

  /**
   * Returns true if and only if this score has the same score as otherScore.
   */
  public boolean isSameScore(Score otherScore) {
    return Objects.equal(this.score, otherScore.score) && this.scaled == otherScore.scaled;
  }
    
  public boolean isNotSet() {
    return this.score == null;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Score [event=").append(event).append(", competitor=").append(competitor)
        .append(", score=").append(score).append(", scaled=").append(scaled).append(", getId()=")
        .append(getId()).append("]");
    return builder.toString();
  }

  @Override
  public int compareTo(Score o) {
    if (Objects.equal(this.score, o.score)) {
      return 0;
    } else {
      if (this.score == null) {
        return -1;
      }
      if (o.score == null) {
        return 1;
      }
      return (int) (this.score - o.score);
    }
  }
  
}
