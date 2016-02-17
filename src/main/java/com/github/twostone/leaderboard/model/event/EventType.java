package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.score.Score;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@SuppressWarnings("serial")
public class EventType extends AbstractEntity {
  
  @JsonFormat(shape = Shape.STRING)
  public enum Ordering {
    ASCENDING {
      @Override
      public com.google.common.collect.Ordering<Score> getComparator() {
        return com.google.common.collect.Ordering.natural();
      }
    },
    DESCENDING {
      @Override
      public com.google.common.collect.Ordering<Score> getComparator() {
        return com.google.common.collect.Ordering.natural().reverse();
      }
    };
    
    public abstract com.google.common.collect.Ordering<Score> getComparator();
  }
  
  private String name;
  @Enumerated(EnumType.ORDINAL)
  private Ordering ordering;

  /**
   * Creates a new event type.
   */
  public EventType(String name, Ordering ordering) {
    super();
    this.name = name;
    this.ordering = ordering;
  }

  EventType() {
    super();
  }

  public String getName() {
    return this.name;
  }
  
  public Ordering getOrdering() {
    return this.ordering;
  }
}
