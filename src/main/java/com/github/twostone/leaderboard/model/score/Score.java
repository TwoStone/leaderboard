package com.github.twostone.leaderboard.model.score;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.event.Event;

import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
@SuppressWarnings("serial")
public class Score extends AbstractEntity {
  
  private Event event;
  
  private Competitor competitor;
  
  private long value;

  @Version
  private long version;
  
  Score() {
    super();
  }
  
  /**
   * Creates a new score.
   */
  public Score(Event event, Competitor competitor, long value) {
    super();
    this.event = event;
    this.competitor = competitor;
    this.value = value;
  }



  public long getVersion() {
    return this.version;
  }

  public Event getEvent() {
    return this.event;
  }

  public Competitor getCompetitor() {
    return this.competitor;
  }
  
  public long getValue() {
    return this.value;
  }
  
  public void setValue(long value) {
    this.value = value;
  }
  
  
}
